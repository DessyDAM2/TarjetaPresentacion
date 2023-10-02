package com.iessanalberto.tarjetaPresentacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iessanalberto.tarjetaPresentacion.ui.theme.TarjetaPresentacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TarjetaPresentacionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Green
                ) {
                    Presentacion(
                        name = "Dessy",
                        proffesion = "Multiplataform developper",
                        telephone = "(555) 555 55 555",
                        link = "https://iessanalbertomagno.catedu.es/",
                        email = "200076@iessanalberto.com"
                    )
                }
            }
        }
    }
}

@Composable
fun Presentacion(
    name: String,
    proffesion: String,
    telephone: String,
    link: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box {
            Image(
                painter = painterResource(id = R.drawable.android_logo),
                contentDescription = "",
                modifier
                    .background(
                        Color.Black
                    )
                    .size(200.dp)
            )
        }

        Text(text = name, fontSize = 40.sp, textAlign = TextAlign.Center)
        Text(text = proffesion, textAlign = TextAlign.Center)
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(text = telephone)
        }
        Row {
            HyperlinkInSentenceExample(link)
        }
        Row {
            Text(text = email)
        }
    }
}

@Composable
fun HyperlinkInSentenceExample(link:String) {
    val sourceText = "Check out my website: "
    val annotatedString = buildAnnotatedString {
        append(sourceText)
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Blue)) {
            append(link)
            addStringAnnotation(
                tag = "URL",
                annotation = link,
                start = length - link.length,
                end = length
            )
        }
    }
    val uriHandler = LocalUriHandler.current
    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    uriHandler.openUri(annotation.item)
                }
        }
    )
}


