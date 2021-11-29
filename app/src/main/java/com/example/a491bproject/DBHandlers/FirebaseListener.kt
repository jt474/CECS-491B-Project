package com.example.a491bproject.DBHandlers

import com.example.a491bproject.models.UserRecipesModel
import java.util.*

interface FirebaseListener {
        fun onSuccess(obj: Any)
        fun onFailure(obj: Any)
}