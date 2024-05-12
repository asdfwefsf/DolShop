package com.company.designsystem.designsystem.component.card

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.company.designsystem.designsystem.component.coil.LoadImageWithCoil

@Composable
fun SomenailCard(
    image: String,
    context: Context,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(200.dp)
            .padding(8.dp)
            .clickable(onClick = onClick),

        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Box(
            modifier = Modifier.padding(8.dp).fillMaxSize(),
            contentAlignment = Alignment.Center
            ) {
            LoadImageWithCoil(image, context)
        }
    }
}

