package com.jakan.uirfood.factory;

import com.jakan.uirfood.bean.User;
import com.jakan.uirfood.exception.FonctionNotFoundException;
import com.jakan.uirfood.factory.bean.Cuisinier;
import com.jakan.uirfood.factory.bean.Etudiant;
import com.jakan.uirfood.factory.bean.Personnel;
import com.jakan.uirfood.factory.bean.PersonnelCuisine;

public class UserFactory {
    public static User getInstanceOf(String fonction) {
        if (fonction == null) {
            return null;
        }

        switch (fonction) {
            case "Etudiant":
                return new Etudiant();
            case "Personnel":
                return new Personnel();
            case "Cuisinier":
                return new Cuisinier();
            case "PersonnelCuisine":
                return new PersonnelCuisine();
            default:
                throw new FonctionNotFoundException(fonction);
        }
    }
}
