package com.example.univcommcompose.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.univcommcompose.domain.model.Post
import com.example.univcommcompose.domain.model.Question

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionListItem(question: Question) {
    Card(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.Person,
                    modifier = Modifier.padding(end = 8.dp),
                    contentDescription = "Person"
                )

                Text(
                    text = question.user.first_name + " " + question.user.last_name,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = question.user.roll_no,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis
            )


            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = question.question,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }


}