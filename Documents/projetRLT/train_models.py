import joblib
import numpy as np
from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier
from sklearn.linear_model import Lasso
from sklearn.model_selection import train_test_split
from sklearn.datasets import load_breast_cancer

# Charger un dataset d'exemple
data = load_breast_cancer()
X, y = data.data, data.target
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

# Fonction pour appliquer le muting sur les données
def apply_muting(X, muting_type='None'):
    if muting_type == 'None':
        return X
    elif muting_type == 'Moderate':
        columns_to_remove = np.random.choice(X.shape[1], size=int(X.shape[1] * 0.2), replace=False)
        X[:, columns_to_remove] = 0
    elif muting_type == 'Aggressive':
        columns_to_remove = np.random.choice(X.shape[1], size=int(X.shape[1] * 0.5), replace=False)
        X[:, columns_to_remove] = 0
    return X

# Fonction pour entraîner un modèle RLT avec muting
def train_rlt_model(X_train, y_train, muting_type='None', model_name='rlt_model.pkl'):
    X_train_muted = apply_muting(X_train, muting_type)
    rlt_model = RandomForestClassifier(n_estimators=100, random_state=42)
    rlt_model.fit(X_train_muted, y_train)
    joblib.dump(rlt_model, model_name)
    print(f"Modèle RLT avec {muting_type} muting sauvegardé sous '{model_name}'")
    return rlt_model

# Entraîner et sauvegarder les modèles
rf = RandomForestClassifier(n_estimators=100, random_state=42)
rf.fit(X_train, y_train)
joblib.dump(rf, 'rf_model.pkl')

rf_sqrt = RandomForestClassifier(n_estimators=100, max_features="sqrt", random_state=42)
rf_sqrt.fit(X_train, y_train)
joblib.dump(rf_sqrt, 'rf_sqrt_model.pkl')

rf_log = RandomForestClassifier(n_estimators=100, max_features="log2", random_state=42)
rf_log.fit(X_train, y_train)
joblib.dump(rf_log, 'rf_log_model.pkl')

et = RandomForestClassifier(n_estimators=100, max_depth=10, random_state=42)
et.fit(X_train, y_train)
joblib.dump(et, 'et_model.pkl')

boosting = GradientBoostingClassifier(n_estimators=100, random_state=42)
boosting.fit(X_train, y_train)
joblib.dump(boosting, 'boosting_model.pkl')

lasso = Lasso(alpha=0.01)
lasso.fit(X_train, y_train)
joblib.dump(lasso, 'lasso_model.pkl')

# Entraîner et sauvegarder les modèles RLT avec différentes stratégies de muting
train_rlt_model(X_train, y_train, muting_type='None', model_name='rlt_model_1.pkl')
train_rlt_model(X_train, y_train, muting_type='Moderate', model_name='rlt_model_2.pkl')
train_rlt_model(X_train, y_train, muting_type='Aggressive', model_name='rlt_model_5.pkl')
