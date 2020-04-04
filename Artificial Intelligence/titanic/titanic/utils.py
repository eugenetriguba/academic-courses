from pathlib import Path

import matplotlib as plt
import pandas as pd


def save_to_plots_directory(plot: plt, filename: str) -> None:
    plot.savefig(Path(__file__).parent.parent / "plots" / filename)


def load_titanic_data(path: Path = Path(__file__).parent.parent / "dataset") -> tuple:
    train = path / "train.csv"
    test = path / "test.csv"
    return pd.read_csv(train), pd.read_csv(test)

