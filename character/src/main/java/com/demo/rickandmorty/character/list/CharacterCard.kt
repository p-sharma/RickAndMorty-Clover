package com.demo.rickandmorty.character.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.demo.rickandmorty.character.R
import java.util.Locale

@ExperimentalMaterial3Api
@Composable
internal fun CharacterCard(
    modifier: Modifier = Modifier,
    id: Int = 0,
    image: String = "",
    name: String = "",
    status: String = "",
    species: String = "",
    cardShape: Shape = ShapeDefaults.Small,
    onCardClicked: (Int) -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .clip(cardShape)
            .clickable { onCardClicked(id) }
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(cardShape),
                model = image,
                contentDescription = "Character Image",
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(R.drawable.female),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                color = colorStatus(status),
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = status.toTitleCase(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        ),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = species.toTitleCase(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        ),
                    )
                }
            }
        }
    }
}

private fun String.toTitleCase() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
}

private fun colorStatus(charStatus: String): Color {
    return when {
        charStatus.contains("Alive") -> Color.Green
        charStatus.contains("Dead") -> Color.Red
        else -> Color.DarkGray
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CardPreview() {
    CharacterCard(
        modifier = Modifier,
        id = 0,
        name = "Rick",
        species = "Human",
        status = "Alive",
        cardShape = ShapeDefaults.Small,
        onCardClicked = {},
    )
}