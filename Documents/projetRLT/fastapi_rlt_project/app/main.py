from fastapi import FastAPI
from pydantic import BaseModel
from models import compare_models  # Import the function from models.py

app = FastAPI()

# Request model to specify dataset, classic model, and RLT model
class ModelRequest(BaseModel):
    dataset: str
    classic_model: str
    rlt_model: str
    muting: str

@app.post("/compare")
async def compare(request: ModelRequest):
    dataset = request.dataset
    classic_model = request.classic_model
    rlt_model = request.rlt_model
    muting = request.muting
    
    # Call the compare_models function from models.py
    comparison_result = compare_models(dataset, classic_model, rlt_model, muting)
    
    return {"result": comparison_result}
