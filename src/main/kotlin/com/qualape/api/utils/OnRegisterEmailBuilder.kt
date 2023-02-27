package com.qualape.api.utils

import com.qualape.api.core.models.Email

class OnRegisterEmailBuilder {

    private fun buildTitle(userName: String): String {
        return "$userName, seu cadastro foi recebido com sucesso!"
    }

    private fun buildContent(userName: String): String {
        return "Olá $userName!" +
        "\n Seja muito bem-vindo(a) ao nosso app."
    }

    fun buildEmail(userName: String): Email {
        return Email(title = buildTitle(userName), content = buildContent(userName))
    }
}
