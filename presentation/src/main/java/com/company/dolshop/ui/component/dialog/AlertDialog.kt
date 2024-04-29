package com.company.dolshop.ui.component.dialog

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.widget.RatingBar
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.company.designsystem.designsystem.DolShopTheme
import com.company.designsystem.designsystem.Paddings

//data class LeadingIconData(
//    @DrawableRes val iconDrawable: Int,
//    @StringRes val iconContentDescription: Int?
//)

//sealed class DialogText {
//    abstract var text: String?
//    abstract var id: Int?
//
//    class Default() : DialogText() {
//        override var text: String? = null
//        override var id: Int? = null
//
//        constructor(text: String) : this() {
//            this.text = text
//        }
//
//        constructor(text: Int) : this() {
//            this.id = text
//        }
//    }
//
//    class Rating() : DialogText() {
//        override var text: String? = null
//        override var id: Int? = null
//        var rating: Float = 0.0f
//
//        constructor(text: String, rating: Float) : this() {
//            this.text = text
//            this.rating = rating
//        }
//
//        constructor(text: Int, rating: Float) : this() {
//            this.id = text
//            this.rating = rating
//        }
//    }
//}
//
//sealed class DialogTitle(
//    open val text: String
//) {
//    data class Default(
//        override val text: String
//    ) : DialogTitle(text)
//
//    data class Header(
//        override val text: String
//    ) : DialogTitle(text)
//
//    data class Large(
//        override val text: String
//    ) : DialogTitle(text)
//}
//sealed class DialogContent {
//    data class Default(
//        val dialogText: DialogText.Default
//    ) : DialogContent()
//
//    data class Large(
//        val dialogText: DialogText.Default
//    ) : DialogContent()
//
//    data class Rating(
//        val dialogText: DialogText.Rating
//    ) : DialogContent()
//}
sealed class DialogButton(
    open val title: String,
    open val action: (() -> Unit)?
) {
    data class Primary(
        override val title: String,
//        val leadingIconData: LeadingIconData? = null,
        override val action: (() -> Unit)? = null
    ) : DialogButton(title, action)

    data class Secondary(
        override val title: String,
        override val action: (() -> Unit)? = null
    ) : DialogButton(title, action)

}
//data class DialogContentStyle(
//    val textStyle: @Composable () -> TextStyle = { MaterialTheme.typography.titleLarge },
//    val contentTopPadding: Dp = Paddings.xlarge,
//    val contentBottomPadding: Dp = Paddings.extra
//)
//
//val LocalDialogContentStyle = compositionLocalOf { DialogContentStyle() }
//@Composable
//fun getStringFromDialogText(text: DialogText.Default): String =
//    text.id?.let {
//        stringResource(id = it)
//    } ?: text.text ?: ""
//
//@Composable
//fun ColumnScope.NormalTextContent(text: DialogText.Default) {
//    Text(
//        text = getStringFromDialogText(text),
//        modifier = Modifier
//            .padding(
//                top = LocalDialogContentStyle.current.contentTopPadding,
//                bottom = LocalDialogContentStyle.current.contentBottomPadding
//            )
//            .align(Alignment.CenterHorizontally),
//        textAlign = TextAlign.Center,
//        style = LocalDialogContentStyle.current.textStyle.invoke()
//    )
//}
//@Composable
//fun ColumnScope.DialogContentWrapper(content: DialogContent) {
//    when (content) {
//        is DialogContent.Default -> {
//            CompositionLocalProvider(
//                LocalDialogContentStyle provides DialogContentStyle(
//                    textStyle = {
//                        MaterialTheme.typography.titleLarge.copy(
//                            lineHeight = 1.6.em
//                        )
//                    },
//                    contentTopPadding = Paddings.small,
//                    contentBottomPadding = Paddings.extra
//                )
//            ) {
//                NormalTextContent(content.dialogText)
//            }
//        }
//
//        is DialogContent.Large -> {
//            CompositionLocalProvider(
//                LocalDialogContentStyle provides DialogContentStyle(
//                    textStyle = {
//                        MaterialTheme.typography.titleLarge.copy(
//                            lineHeight = 1.6.em
//                        )
//                    },
//                    contentTopPadding = Paddings.extra,
//                    contentBottomPadding = Paddings.extra
//                )
//            ) {
//                NormalTextContent(content.dialogText)
//            }
//        }
//
//        is DialogContent.Rating -> {
//            RatingContent(content.dialogText)
//        }
//    }
//}
//@Composable
//fun RatingContent(content: DialogText.Rating) {
//    Column(
//        modifier = Modifier.padding(
//            top = Paddings.large,
//            bottom = Paddings.xlarge
//        )
//    ) {
//        RatingTable(
//            rating = content.rating,
//            movieTitle = content.text ?: ""
//        )
//    }
//}
//@Composable
//fun getAnnotatedText(text: String): AnnotatedString {
//    val spanned = remember(text) {
//        HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
//    }
//    return remember(spanned) {
//        buildAnnotatedString {
//            append(spanned.toString())
//            spanned.getSpans(0, spanned.length, Any::class.java).forEach { span ->
//                val start = spanned.getSpanStart(span)
//                val end = spanned.getSpanEnd(span)
//                when (span) {
//                    is StyleSpan -> when (span.style) {
//                        Typeface.BOLD -> addStyle(
//                            SpanStyle(fontWeight = FontWeight.Bold),
//                            start,
//                            end
//                        )
//
//                        Typeface.ITALIC -> addStyle(
//                            SpanStyle(fontStyle = FontStyle.Italic),
//                            start,
//                            end
//                        )
//
//                        Typeface.BOLD_ITALIC -> addStyle(
//                            SpanStyle(
//                                fontWeight = FontWeight.Bold,
//                                fontStyle = FontStyle.Italic,
//                            ),
//                            start,
//                            end,
//                        )
//                    }
//
//                    is UnderlineSpan -> addStyle(
//                        SpanStyle(textDecoration = TextDecoration.Underline),
//                        start,
//                        end
//                    )
//
//                    is ForegroundColorSpan -> addStyle(
//                        SpanStyle(color = Color(span.foregroundColor)),
//                        start,
//                        end
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ColumnScope.RatingTable(
//    rating: Float,
//    movieTitle: String
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .align(Alignment.CenterHorizontally)
//    ) {
//        Text(
//            modifier = Modifier.align(
//                Alignment.CenterHorizontally
//            ),
//            text = getAnnotatedText(text = movieTitle),
//
//            textAlign = TextAlign.Center
//        )
//        Spacer(Modifier.height(Paddings.large))
//        StarRatingBar(
//            score = rating
//        )
//    }
//}
//@Composable
//fun ColumnScope.StarRatingBar(score: Float) {
//    Card(
//        modifier = androidx.compose.ui.Modifier
//            .padding(
//                Paddings.medium
//            )
//            .wrapContentSize()
//            .align(Alignment.CenterHorizontally),
//    ) {
//        Box(
//            contentAlignment = Alignment.Center
//        ) {
//            val foregroundColor = MaterialTheme.colorScheme.primary.toArgb()
//            val starBackgroundColor = MaterialTheme.colorScheme.primary.copy(
//                alpha = 0.2f
//            ).toArgb()
//
//
//            AndroidView(
//                modifier = Modifier
//                    .wrapContentWidth()
//                    .align(Alignment.Center),
//                factory = {
//                    RatingBar(
//                        it
//                    ).apply {
//                        max = 10
//                        stepSize = 25f
//                        rating = score
//                        numStars = 5
//                        progressTintList = ColorStateList.valueOf(foregroundColor)
//                        progressBackgroundTintList = ColorStateList.valueOf(starBackgroundColor)
//                    }
//                }
//            )
//        }
//    }
//}
//@Composable
//fun DialogTitleWrapper(title: DialogTitle) {
//    when (title) {
//        is DialogTitle.Default -> DefaultDialogTitle(title)
//        is DialogTitle.Large -> LargeDialogTitle(title)
//        is DialogTitle.Header -> HeaderDialogTitle(title)
//    }
//}
//@Composable
//fun HeaderDialogTitle(title: DialogTitle.Header) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.primary)
//            .padding(Paddings.large),
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = title.text,
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.displayLarge.copy(
//                color = MaterialTheme.colorScheme.onPrimary
//            )
//        )
//    }
//}
//
//@Composable
//fun LargeDialogTitle(title: DialogTitle.Large) {
//    Column(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Text(
//            text = title.text,
//            modifier = Modifier
//                .padding(all = Paddings.xlarge)
//                .align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.titleLarge,
//            color = MaterialTheme.colorScheme.secondary
//        )
//    }
//}
//
//@Composable
//fun DefaultDialogTitle(title: DialogTitle.Default) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.onPrimary)
//            .padding(
//                horizontal = Paddings.large,
//                vertical = Paddings.extra
//            ),
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            title.text,
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.headlineLarge.copy(
//                color = MaterialTheme.colorScheme.secondary
//            )
//        )
//    }
//}
//@Composable
//fun BaseDialogPopup(
//    dialogTitle: DialogTitle? = null,
//    dialogContent: DialogContent? = null,
//    buttons: List<DialogButton>? = null
//) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = MaterialTheme.shapes.large
//    ) {
//        Column(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            dialogTitle?.let {
//                DialogTitleWrapper(it)
//            }
//            Column(
//                modifier = Modifier
//                    .background(Color.Transparent)
//                    .fillMaxWidth()
//                    .padding(
//                        start = Paddings.xlarge,
//                        end = Paddings.xlarge,
//                        bottom = Paddings.xlarge
//                    )
//            ) {
//                dialogContent?.let { DialogContentWrapper(it) }
//                buttons?.let { DialogButtonsColumn(it) }
//            }
//        }
//    }
//}
//@Composable
//fun DialogButtonsColumn(
//    buttons: List<DialogButton>?
//) {
//    Column(
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        buttons?.forEachIndexed { index, dialogButton ->
//            if (index > 0) {
//                Spacer(modifier = Modifier.height(Paddings.large))
//            }
//            when (dialogButton) {
//                is DialogButton.Primary -> {
//                    PrimaryButton(
//                        text = dialogButton.title,
////                        leadingIconData = dialogButton.leadingIconData
//                    ) { dialogButton.action?.invoke() }
//                }
//
//                is DialogButton.Secondary -> {
//                    SecondaryButton(
//                        text = dialogButton.title
//                    ) { dialogButton.action?.invoke() }
//                }
//
//                is DialogButton.SecondaryBorderless -> {
//                    SecondaryBorderlessButton(
//                        text = dialogButton.title
//                    ) { dialogButton.action?.invoke() }
//                }
//
//                is DialogButton.UnderlinedText -> {
//                    UnderlinedTextButton(
//                        text = dialogButton.title
//                    ) { dialogButton.action?.invoke() }
//                }
//            }
//        }
//    }
//}
//@Composable
//fun SecondaryBorderlessButton(
//    modifier: Modifier = Modifier,
//    @StringRes id: Int? = null,
//    text: String = "",
//    onClick: () -> Unit
//) {
//    Button(
//        modifier = modifier.fillMaxWidth(),
//        shape = MaterialTheme.shapes.large,
//        onClick = onClick,
//        colors = ButtonDefaults.buttonColors(
//            contentColor = MaterialTheme.colorScheme.secondary,
//            disabledContentColor = MaterialTheme.colorScheme.background,
//        ),
//        elevation = null
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = id?.let { stringResource(id = id) } ?: text,
//                modifier = Modifier.padding(Paddings.small)
//            )
//        }
//    }
//}
//val LEADING_ICON_SIZE = 24.dp
//@Composable
//fun UnderlinedTextButton(
//    modifier: Modifier = Modifier,
//    @StringRes id: Int? = null,
//    text: String = "",
//    onClick: () -> Unit
//) {
//    Button(
//        modifier = modifier
//            .fillMaxWidth(),
//        shape = MaterialTheme.shapes.large,
//        onClick = onClick,
//        colors = ButtonDefaults.buttonColors(
//            contentColor = MaterialTheme.colorScheme.secondary,
//            disabledContentColor = MaterialTheme.colorScheme.background,
//        ),
//        elevation = null
//    ) {
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = id?.let { stringResource(id = id) } ?: text,
//                modifier = Modifier.padding(Paddings.small)
//            )
//        }
//    }
//}
@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
//    leadingIconData: LeadingIconData? = null,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
//            leadingIconData?.let {
//                Icon(
//                    modifier = Modifier.size(LEADING_ICON_SIZE),
//                    painter = painterResource(id = leadingIconData.iconDrawable),
//                    contentDescription = leadingIconData.iconContentDescription?.let { desc ->
//                        stringResource(
//                            id = desc
//                        )
//                    }
//                )
//            }
            Text(
                text = id?.let { stringResource(id = id) } ?: text,
                modifier = Modifier.padding(com.company.designsystem.designsystem.Paddings.small)
            )
        }
    }
}
@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
        border = BorderStroke(
            2.dp,
            MaterialTheme.colorScheme.secondary
        ),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = id?.let { stringResource(id = id) } ?: text,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(com.company.designsystem.designsystem.Paddings.small)
            )
        }
    }
}
//
//
//@Preview
//@Composable
//fun BaseDialogPopupPreview() {
//    DolShopTheme {
//        BaseDialogPopup(
//            dialogTitle = DialogTitle.Header("TITLE"),
//            dialogContent = DialogContent.Large(
//                DialogText.Default("abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde")
//            ),
//            buttons = listOf(
//                DialogButton.Primary("Okay") {}
//            )
//        )
//    }
//}
//
//@Preview
//@Composable
//fun BaseDialogPopupPreview2() {
//    DolShopTheme {
//        BaseDialogPopup(
//            dialogTitle = DialogTitle.Large("TITLE"),
//            dialogContent = DialogContent.Default(
//                DialogText.Default("abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde")
//            ),
//            buttons = listOf(
//                DialogButton.Secondary("Okay") {},
//                DialogButton.UnderlinedText("Cancel") {}
//            )
//        )
//    }
//}
//
//@Preview
//@Composable
//fun BaseDialogPopupPreview3() {
//    DolShopTheme {
//        BaseDialogPopup(
//            dialogTitle = DialogTitle.Large("TITLE"),
//            dialogContent = DialogContent.Rating(
//                DialogText.Rating(
//                    text = "Jurassic Park",
//                    rating = 8.2f
//                )
//            ),
//            buttons = listOf(
//                DialogButton.Primary("Okay") {},
//                DialogButton.Secondary("Cancel") {}
//            )
//        )
//    }
//}
