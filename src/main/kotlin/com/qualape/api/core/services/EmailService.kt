//package com.qualape.api.core.repositories
//
//import com.qualape.api.core.models.Email
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.mail.SimpleMailMessage
//import org.springframework.mail.javamail.JavaMailSender
//import org.springframework.stereotype.Service
//
//@Service
//class EmailService @Autowired constructor(
//    private val javaMailSender: JavaMailSender
//) {
//
//    fun send(addressee: String, email: Email) {
//        val simpleMailMessage = SimpleMailMessage().apply {
//            setTo(addressee)
//            subject = email.title
//            text = email.content
//        }
//        javaMailSender.send(simpleMailMessage)
//    }
//}