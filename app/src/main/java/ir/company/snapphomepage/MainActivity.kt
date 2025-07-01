package ir.company.snapphomepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_6
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnappHomeUI()
        }
    }
}


@Composable
fun SnappHomeUI() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            GridItemSpan()
            Spacer(modifier = Modifier.height(20.dp))
            BannerItem()
            Spacer(modifier = Modifier.height(30.dp))
            EdibleItem()
            Spacer(modifier = Modifier.height(20.dp))
            BannerItem()

        }



        BottomNavIsland()
    }
}

@Composable
fun GridItemSpan() {
    val items = listOf(
        snappItems(R.drawable.food, "غذا", false, false, Color.Red),
        snappItems(R.drawable.taxi, "اسنپ", false, false, Color.Green),
        snappItems(R.drawable.doctor, "دکتر", false, false, Color.Green),
        snappItems(R.drawable.credit, "اسنپ پی", false, false, Color.Blue),
        snappItems(R.drawable.bike, "اسنپ باکس", false, false, Color.Green),
        snappItems(R.drawable.hotel, "هتل", false, false, Color.Red),
        snappItems(R.drawable.moving, "کامیون", false, false, Color.Blue),
        snappItems(R.drawable.food, "غذا", false, false, Color.Green),
        snappItems(R.drawable.credit, "اسنپ پی", false, false, Color.Green),
        snappItems(R.drawable.pickup, "وانت", false, false, Color.Red),
        snappItems(R.drawable.food, "غذا", false, false, Color.Red),
        snappItems(R.drawable.bike, "اسنپ باکس", false, false, Color.Blue)
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items) { items ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ItemPages(
                    items.icon,
                    items.label,
                    items.processBox,
                    items.offer,
                    items.color
                )
            }

        }
    }
}

@Composable
fun ItemPages(icon: Int, label: String, processBox: Boolean, offer: Boolean, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {


        Box(
            modifier = Modifier.size(48.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            if (processBox) {
                Box(
                    modifier = Modifier
                        .size(20.dp, 8.dp)
                        .offset(x = (-8).dp)
                        .background(color, shape = CircleShape)
                        .border(1.dp, Color.White, CircleShape)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .size(3.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                            )
                        }
                    }
                }
            }
            if (offer) {
                Box(
                    modifier = Modifier
                        .size(45.dp, 14.dp)
                        .offset(x = (-3).dp, y = -45.dp)
                        .background(Color.Red, shape = CircleShape)
                        .border(1.dp, Color.White, CircleShape),
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Text(
                            "80%تخفیف",
                            fontSize = 8.sp,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(1.dp),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(label)
    }
}

@Composable
fun BannerItem() {
    Surface(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}


@Composable
fun EdibleItem(){
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .horizontalScroll(rememberScrollState()),
        ) {
            repeat(5) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(horizontal = 10.dp).size(100.dp),
                    shadowElevation = 8.dp,

                    ) {
                    Image(
                        painter = painterResource(R.drawable.banana),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}


@Composable
fun BottomNavIsland(){
    val BottomNav = listOf(
        snappItems(R.drawable.credit, "اسنپ پی", false, false, Color.Blue),
        snappItems(R.drawable.bike, "اسنپ باکس", false, false, Color.Green),
        snappItems(R.drawable.food, "غذا", false, false, Color.Green),
        snappItems(R.drawable.taxi, "اسنپ", false, false, Color.Red),
    )
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .size(300.dp,120.dp)
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.Transparent,
                        0.5f to Color.White,
                        1.0f to Color.White
                    )
                )
            )
    ) {
        Surface(
            modifier = Modifier
                .shadow(elevation = 12.dp, shape = RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(20.dp),
            color = Color.White
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,

                ){
                repeat(BottomNav.size){
                    ItemPages(BottomNav[it].icon,BottomNav[it].label,BottomNav[it].processBox,BottomNav[it].offer,BottomNav[it].color)
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    device = PIXEL_6,
    showSystemUi = true
)
@Composable
fun SnappPreview() {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

    }
    SnappHomeUI()

}