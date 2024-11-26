package com.example.contactos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactListScreen()
        }
    }
}

data class Contact(val name: String, val phoneNumber: String)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactListScreen() {
    val contacts = listOf(
        Contact("Ana", "612-34-56-78"),
        Contact("Alberto", "615-78-90-12"),
        Contact("Carlos", "635-12-34-56"),
        Contact("Clara", "616-22-33-44"),
        Contact("David", "617-45-67-89"),
        Contact("Diana", "618-56-78-90"),
        Contact("Eva", "619-23-45-67"),
        Contact("Esteban", "620-67-89-01"),
        Contact("Felipe", "621-23-45-67"),
        Contact("Francisco", "622-45-67-89"),
        Contact("Gina", "623-56-78-90"),
        Contact("Guillermo", "624-67-89-01")
    )

    val groupedContacts = contacts.groupBy { it.name.first() }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        groupedContacts.keys.sorted().forEach { initial ->
            stickyHeader {
                Text(
                    text = initial.toString(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }

            items(groupedContacts[initial]!!) { contact ->
                ContactItem(contact)
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp)
            .clip(MaterialTheme.shapes.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = contact.name,
            modifier = Modifier
                .size(50.dp)
                .padding(end = 16.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = contact.phoneNumber,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ContactListScreen()
}
