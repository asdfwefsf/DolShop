package com.company.dolshop.screens.screentype.mypagescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.dolshop.ui.theme.DolShopTheme

@Composable
@ExperimentalMaterial3Api
fun InputAddressInfoScreen() {
    var addressName by remember { mutableStateOf("") }
    var addressNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var detailedAddress by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("배송지 관련 정보 추가")

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = addressName,
            onValueChange = {addressName = it},
            label = { Text("배송지명 (선택)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Postal Code Entry
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            OutlinedTextField(
                value = addressNumber,
                onValueChange = {addressNumber = it},
                label = { Text("우편번호") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { /* Open postal code search */ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("조회")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = address,
            onValueChange = {address = it},
            label = { Text("주소") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = detailedAddress,
            onValueChange = {detailedAddress = it},
            label = { Text("상세주소") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {phoneNumber = it},
            label = { Text("전화번호") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Save address information */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("저장")
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun InputAddressInfoScreenPreview() {
    DolShopTheme {
        InputAddressInfoScreen()
    }
}