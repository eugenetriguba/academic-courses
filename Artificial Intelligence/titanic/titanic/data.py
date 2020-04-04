"""This modules deals with cleaning up the titanic data."""
import re

import numpy as np
from pandas.core.frame import DataFrame


def clean_data(data: DataFrame) -> DataFrame:
    """
    Clean the titanic data by converting all the data
    into int64s.

    Drops the passenger id since it does not contribute
    to survivability.

    We drop the ticket attribute since the vast majority of them
    are unique, which makes it tough to convert them into something
    useful.

    Cabin looks like "A431" where A refers to the deck.
    So we're extracting the persons deck from that attribute,
    even though there are a lot of missing ones, and filling
    the missing ones with 0.

    The missing ages are filling using the random numbers from
    the mean and standard deviation. This does it for each dataset
    individually so it may be improved if we combined them to find
    the mean and std.

    :param data: a pandas data frame of the titanic data
    :return:
    """
    data = data.drop(["PassengerId"], axis=1)
    data = data.drop(["Ticket"], axis=1)

    # Most common value (only 2 missing)
    data["Embarked"] = data["Embarked"].fillna("S")
    ports = {
        "S": 0,
        "C": 1,
        "Q": 2
    }
    data["Embarked"] = data["Embarked"].map(ports)

    data = _convert_cabin_to_deck(data)
    data = _fill_age(data)

    data["Fare"] = data["Fare"].fillna(0)
    data["Fare"] = data["Fare"].astype(int)

    data = _convert_names_to_titles(data)

    genders = {
        "male": 0,
        "female": 1
    }
    data["Sex"] = data["Sex"].map(genders)

    data["AgeClass"] = data["Age"] * data["Pclass"]

    return data


def _convert_cabin_to_deck(data: DataFrame) -> DataFrame:
    """
    Cabin had the majority of its data missing, but it
    contains the deck information so we're converting
    it to preserve that info and filling the rest with 0.
    """
    deck = {
        "A": 1,
        "B": 2,
        "C": 3,
        "D": 4,
        "E": 5,
        "F": 6,
        "G": 7,
        "Z": 8  # NA value. Titanic decks range from A-G
    }

    data["Cabin"] = data["Cabin"].fillna("Z0")
    data["Deck"] = data["Cabin"].map(
        lambda x: re.compile("([a-zA-Z]+)").search(x).group()
    )
    data["Deck"] = data["Deck"].map(deck)
    data["Deck"] = data["Deck"].fillna(0)
    data["Deck"] = data["Deck"].astype(int)

    data = data.drop(["Cabin"], axis=1)
    return data


def _fill_age(data: DataFrame) -> DataFrame:
    """Fills the age with a random number within std of the mean."""
    mean = data["Age"].mean()
    std = data["Age"].std()
    empty = data["Age"].isnull().sum()

    copy = data["Age"].copy()
    copy[np.isnan(copy)] = np.random.randint(
        mean - std, mean + std, size=empty
    )
    data["Age"] = copy
    data["Age"] = data["Age"].astype(int)

    return data


def _convert_names_to_titles(data: DataFrame) -> DataFrame:
    """
    The names on their own don't mean a ton in terms of survivability,
    but we can gleam something by keeping the titles.
    """
    titles = {
        "Mr": 1,
        "Miss": 2,
        "Mrs": 3,
        "Master": 4,
        "Uncommon": 5
    }

    data["Title"] = data.Name.str.extract(" ([A-Za-z]+)\.", expand=False)
    data["Title"] = data["Title"].replace(
        [
            "Lady", "Countess", "Capt", "Col", "Don",
            "Dr", "Major", "Rev", "Sir", "Jonkheer", "Dona"
        ], "Uncommon"
    )
    data["Title"] = data["Title"].replace("Mlle", "Miss")
    data["Title"] = data["Title"].replace("Ms", "Miss")
    data["Title"] = data["Title"].replace("Mme", "Mrs")

    data["Title"] = data["Title"].map(titles)
    data["Title"] = data["Title"].fillna(0)
    data = data.drop(["Name"], axis=1)

    return data
