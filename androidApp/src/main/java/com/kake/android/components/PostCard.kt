package com.kake.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kake.base.models.Category
import com.kake.base.models.Post
import com.kake.base.util.convertLongToDate
import com.kake.base.util.decodeThumbnailImage

@Composable
fun PostCard(
    post: Post,
    onPostClick: (String) -> Unit
) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 12.dp))
            .clickable { onPostClick(post._id) },
        tonalElevation = 1.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if (post.thumbnail.contains("http")) {
                AsyncImage(
                    modifier = Modifier.height(260.dp),
                    model = ImageRequest
                        .Builder(context)
                        .data( post.thumbnail)
                        .build(),
                    contentDescription = "Post Thumbnail",
                    contentScale = ContentScale.Crop
                )
            } else if(post.thumbnail.decodeThumbnailImage()?.bitmap?.asImageBitmap() != null){
                Image(bitmap = post.thumbnail.decodeThumbnailImage()?.bitmap?.asImageBitmap()!!,
                    contentDescription = null,
                    modifier = Modifier.height(260.dp).fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .alpha(0.5f),
                    text = post.date.convertLongToDate(),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = post.title,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = post.subtitle,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
                SuggestionChip(
                    onClick = { },
                    label = { Text(text = Category.valueOf(post.category).name) }
                )
            }
        }
    }
}

@Composable
fun PostCardsView(
    posts: List<Post>,
    topMargin: Dp,
    hideMessage: Boolean = false,
    onPostClick: (String) -> Unit
) {
    if (posts.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topMargin)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = posts,
                key = { post -> post._id }
            ) { post ->
                PostCard(post = post, onPostClick = onPostClick)
            }
        }
    } else {
        EmptyUI()
    }
}