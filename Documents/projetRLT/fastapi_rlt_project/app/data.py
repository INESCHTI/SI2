import pandas as pd

def load_data(dataset_name: str):
    if dataset_name == 'boston':
        return pd.read_csv('path/to/boston.csv')
    elif dataset_name == 'parkinsons':
        return pd.read_csv('path/to/parkinsons.csv')
    elif dataset_name == 'diabetes':
        return pd.read_csv('path/to/diabetes.csv')
    elif dataset_name == 'heart':
        return pd.read_csv('path/to/heart.csv')
    elif dataset_name == 'sonar':
        return pd.read_csv('path/to/sonar.csv')
    elif dataset_name == 'wine':
        return pd.read_csv('path/to/wine.csv')
    elif dataset_name == 'cancer':
        return pd.read_csv('path/to/cancer.csv')
    elif dataset_name == 'iris':
        return pd.read_csv('path/to/iris.csv')
    elif dataset_name == 'titanic':
        return pd.read_csv('path/to/titanic.csv')
    elif dataset_name == 'breast_cancer':
        return pd.read_csv('path/to/breast_cancer.csv')
    
    else:
        raise ValueError("Dataset not found")
