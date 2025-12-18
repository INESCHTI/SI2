from fastapi import FastAPI, HTTPException
import json
from typing import List, Dict, Any
from pydantic import BaseModel

app = FastAPI(title="RLT vs Classic Models API")

# Modèles de données pour la documentation automatique
class RLTResult(BaseModel):
    k: int
    muting: str
    score: float

class ComparisonResponse(BaseModel):
    dataset: str
    classic_models: Dict[str, float]
    rlt_models: List[RLTResult]

# Charger les données au démarrage
try:
    with open("model_results.json", "r") as f:
        DB = json.load(f)
except FileNotFoundError:
    DB = {"datasets": [], "metrics": {}}

@app.get("/")
def read_root():
    return {"message": "API de Déploiement RLT active"}

@app.get("/datasets", response_model=List[str])
def get_datasets():
    """Retourne la liste des datasets disponibles"""
    return DB.get("datasets", [])

@app.get("/results/{dataset_name}", response_model=ComparisonResponse)
def get_results(dataset_name: str):
    """Retourne la comparaison pour un dataset spécifique"""
    if dataset_name not in DB["metrics"]:
        raise HTTPException(status_code=404, detail="Dataset non trouvé")
    
    data = DB["metrics"][dataset_name]
    return {
        "dataset": dataset_name,
        "classic_models": data["classic_models"],
        "rlt_models": data["rlt_models"]
    }

# Pour lancer : uvicorn main:app --reload --host 0.0.0.0