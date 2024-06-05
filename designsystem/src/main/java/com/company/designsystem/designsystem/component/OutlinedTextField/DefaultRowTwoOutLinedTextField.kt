package com.company.designsystem.designsystem.component.OutlinedTextField

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

//
@Composable
fun DefaultRowTwoOutLinedTextField(key : String , placeholder : String , value : String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, bottom = 4.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(key, color = Color.Black, modifier = Modifier.weight(1f))

        OutlinedTextField(
            placeholder = { Text(text = placeholder , color = Color.Gray) },

            value = value,
            onValueChange = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
                .padding(start = 4.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Black,
                focusedIndicatorColor = Color.Black
            ),
            readOnly = true,

        )
    }
}