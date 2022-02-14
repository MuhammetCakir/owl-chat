package com.mcakir.owlchat.entry

import com.mcakir.owlchat.entry.entity.User

interface EntryContracts {

    interface View {

        /**
         * Shows a status message on entry screen.
         *
         * @param message The message string will be displayed.
         *
         * @since 0.1.0
         */
        fun showStatusMessage(message: String)
    }

    interface Presenter {

        /**
         * View layer's onCreate cycle method.
         *
         * @since 0.1.0
         */
        fun onCreate()

        /**
         * View layer's onDestroy cycle method.
         *
         * @since 0.1.0
         */
        fun onDestroy()

        /**
         * This method will be called when the user clicked the join button.
         *
         * @since 0.1.0
         */
        fun onJoinClicked(nickname: String?)
    }

    interface Interactor {

        /**
         * This method adds the given user to the Firebase Realtime DB.
         *
         * @param user User entity will be added to DB.
         * @param completion Operation completion block. If the error field is null,
         * it means that the operation is successful.
         *
         * @since 0.1.0
         */
        fun addUser(user: User, completion: (error: String?) -> Unit)
    }

    interface Router {

        /**
         * The app goes to the chat screen.
         *
         * @param nickname User's nickname.
         */
        fun goToChatScreen(nickname: String)
    }
}
