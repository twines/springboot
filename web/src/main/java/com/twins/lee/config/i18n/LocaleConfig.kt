package com.twins.lee.config.i18n

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
open class LocaleConfig :WebMvcConfigurer{
    open class AppLocaleResolver : LocaleResolver {
        override fun setLocale(request: HttpServletRequest, response: HttpServletResponse?, locale: Locale?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun resolveLocale(request: HttpServletRequest): Locale {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            val lang = request.getParameter("lang")
            var locale = Locale.getDefault()

            if (lang.isNotEmpty()) {
                val split = lang.split("_")
                val locale = Locale(split[0], split[1])
            }
            return locale
        }

    }

    @Bean
    open fun localResolver(): LocaleResolver {

        return AppLocaleResolver()
    }

}