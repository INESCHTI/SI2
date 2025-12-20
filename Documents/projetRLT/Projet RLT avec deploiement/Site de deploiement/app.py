"""
Site Web de Déploiement RLT
Application Flask pour comparer les modèles RLT avec RF, Lasso, Gradient Boosting, ExtraTrees
"""
import os
import json
import pickle
import numpy as np
import pandas as pd
from flask import Flask, render_template, request, jsonify
from pathlib import Path

# Import RLT classes so pickle can find them when loading models
from rlt_forest import RLTForest, RLTTree, Node

app = Flask(__name__)

# Chemins vers les fichiers
BASE_DIR = Path(__file__).parent.parent
MODELS_DIR = BASE_DIR / 'models'
METADATA_FILE = BASE_DIR / 'datasets_metadata.json'
PERFORMANCE_FILE = BASE_DIR / 'models_performance.json'
FEATURE_RANGES_FILE = BASE_DIR / 'feature_ranges.json'

# Charger les métadonnées au démarrage
with open(METADATA_FILE, 'r', encoding='utf-8') as f:
    DATASETS_METADATA = json.load(f)

with open(PERFORMANCE_FILE, 'r', encoding='utf-8') as f:
    MODELS_PERFORMANCE = json.load(f)

with open(FEATURE_RANGES_FILE, 'r', encoding='utf-8') as f:
    FEATURE_RANGES = json.load(f)

# Mapper les noms de modèles aux fichiers
MODEL_FILES = {
    'RLT': 'rlt_model.pkl',
    'Random Forest': 'rf_model.pkl',
    'Gradient Boosting': 'gb_model.pkl',
    'ExtraTrees': 'et_model.pkl'
}


@app.route('/')
def index():
    """Page d'accueil"""
    return render_template('index.html')


@app.route('/api/datasets', methods=['GET'])
def get_datasets():
    """Retourne la liste des datasets disponibles"""
    datasets_list = []
    for folder_name, metadata in DATASETS_METADATA.items():
        datasets_list.append({
            'folder_name': folder_name,
            'display_name': metadata['display_name'],
            'task_type': metadata['task_type'],
            'n_samples': metadata['n_samples'],
            'n_features': metadata['n_features']
        })
    return jsonify(datasets_list)


@app.route('/api/dataset/<dataset_name>/info', methods=['GET'])
def get_dataset_info(dataset_name):
    """Retourne les informations détaillées d'un dataset"""
    if dataset_name not in DATASETS_METADATA:
        return jsonify({'error': 'Dataset not found'}), 404
    
    metadata = DATASETS_METADATA[dataset_name]
    performance = MODELS_PERFORMANCE.get(dataset_name, {})
    
    return jsonify({
        'metadata': metadata,
        'performance': performance,
        'feature_ranges': FEATURE_RANGES.get(dataset_name, {})
    })


@app.route('/api/dataset/<dataset_name>/random-fill', methods=['POST'])
def random_fill(dataset_name):
    """Génère des valeurs aléatoires intelligentes pour un dataset"""
    if dataset_name not in FEATURE_RANGES:
        return jsonify({'error': 'Dataset not found'}), 404
    
    ranges = FEATURE_RANGES[dataset_name]
    random_values = {}
    
    for feature_name, feature_info in ranges.items():
        # Générer selon la distribution (normal par défaut)
        if feature_info.get('distribution') == 'normal':
            # Distribution normale avec mean et std
            value = np.random.normal(feature_info['mean'], feature_info['std'])
            # Clipper dans les limites min/max
            value = np.clip(value, feature_info['min'], feature_info['max'])
        else:
            # Distribution uniforme par défaut
            value = np.random.uniform(feature_info['min'], feature_info['max'])
        
        random_values[feature_name] = float(value)
    
    return jsonify(random_values)


@app.route('/api/predict', methods=['POST'])
def predict():
    """
    Effectue des prédictions avec tous les modèles
    Body JSON: {
        "dataset_name": "breast_cancer",
        "features": {"feature1": value1, "feature2": value2, ...}
    }
    """
    data = request.get_json()
    dataset_name = data.get('dataset_name')
    features = data.get('features')
    
    if not dataset_name or not features:
        return jsonify({'error': 'Missing dataset_name or features'}), 400
    
    if dataset_name not in DATASETS_METADATA:
        return jsonify({'error': 'Dataset not found'}), 404
    
    # Charger les métadonnées
    metadata = DATASETS_METADATA[dataset_name]
    feature_names = metadata['feature_names']
    performance = MODELS_PERFORMANCE.get(dataset_name, {})
    
    # Créer le DataFrame avec les features dans le bon ordre
    try:
        X = pd.DataFrame([features], columns=feature_names)
    except Exception as e:
        return jsonify({'error': f'Invalid features: {str(e)}'}), 400
    
    # Charger le scaler
    scaler_path = MODELS_DIR / dataset_name / 'scaler.pkl'
    with open(scaler_path, 'rb') as f:
        scaler = pickle.load(f)
    
    # Scaler les features
    X_scaled = scaler.transform(X)
    X_scaled_df = pd.DataFrame(X_scaled, columns=feature_names)
    
    # Prédictions avec chaque modèle
    predictions = {}
    
    for model_name, model_file in MODEL_FILES.items():
        model_path = MODELS_DIR / dataset_name / model_file
        
        if not model_path.exists():
            continue
        
        try:
            with open(model_path, 'rb') as f:
                model = pickle.load(f)
            
            # Prédiction
            pred = model.predict(X_scaled_df)
            prediction_value = float(pred[0])
            
            # Récupérer les performances historiques
            model_perf = performance.get('models', {}).get(model_name, {})
            
            predictions[model_name] = {
                'prediction': prediction_value,
                'historical_performance': model_perf
            }
        except Exception as e:
            predictions[model_name] = {
                'error': str(e)
            }
    
    # Ajouter Lasso/Ridge selon le type
    if metadata['task_type'] == 'regression':
        lasso_path = MODELS_DIR / dataset_name / 'lasso_model.pkl'
        if lasso_path.exists():
            try:
                with open(lasso_path, 'rb') as f:
                    lasso_model = pickle.load(f)
                pred = lasso_model.predict(X_scaled)
                predictions['Lasso'] = {
                    'prediction': float(pred[0]),
                    'historical_performance': performance.get('models', {}).get('Lasso', {})
                }
            except Exception as e:
                predictions['Lasso'] = {'error': str(e)}
    else:
        ridge_path = MODELS_DIR / dataset_name / 'ridge_model.pkl'
        if ridge_path.exists():
            try:
                with open(ridge_path, 'rb') as f:
                    ridge_model = pickle.load(f)
                pred = ridge_model.predict(X_scaled)
                predictions['Logistic Ridge'] = {
                    'prediction': int(pred[0]),
                    'historical_performance': performance.get('models', {}).get('Logistic Ridge', {})
                }
            except Exception as e:
                predictions['Logistic Ridge'] = {'error': str(e)}
    
    # Debug: Print what models we're returning
    print(f"\n🔍 DEBUG - Models returned: {list(predictions.keys())}")
    for model_name, data in predictions.items():
        if 'error' in data:
            print(f"❌ {model_name}: ERROR - {data['error']}")
        else:
            print(f"✅ {model_name}: Success")
    
    return jsonify({
        'dataset_name': dataset_name,
        'task_type': metadata['task_type'],
        'predictions': predictions
    })


if __name__ == '__main__':
    print("="*80)
    print("🚀 DÉMARRAGE DU SERVEUR WEB RLT")
    print("="*80)
    print(f"📁 Dossier modèles: {MODELS_DIR}")
    print(f"📊 Datasets disponibles: {len(DATASETS_METADATA)}")
    print(f"🌐 Accédez au site: http://localhost:5000")
    print("="*80)
    app.run(debug=True, host='0.0.0.0', port=5000)
