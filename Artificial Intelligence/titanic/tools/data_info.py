import os
import sys

sys.path.append(os.path.join(os.path.dirname(__file__), '../'))

import pandas as pd

from titanic.utils import load_titanic_data

if __name__ == "__main__":
    train_data, test_data = load_titanic_data()
    print(train_data.info())
    print(train_data.describe())
    print(train_data.head(8))

    total = train_data.isnull().sum().sort_values(ascending=False)
    percent = round(
        train_data.isnull().sum() /
        train_data.isnull().count() * 100,
        1
    ).sort_values(ascending=False)
    missing_data = pd.concat([total, percent], axis=1, keys=["Total", "%"])
    print(missing_data.head(4))

