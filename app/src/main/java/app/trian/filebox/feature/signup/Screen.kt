package app.trian.filebox.feature.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.FileBoxState
import app.trian.filebox.R
import app.trian.filebox.components.AnnotationTextItem
import app.trian.filebox.components.TextWithAction
import app.trian.filebox.composables.BottomBarListener
import app.trian.filebox.rememberFileBoxApplication
import app.trian.filebox.ui.theme.FileBoxTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ScreenSignUp(
    modifier: Modifier=Modifier,
    goToSingIn:()->Unit={},
    onSubmit:(String, String)->Unit={ _, _->}
){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(R.string.label_input_name_sign_up)) },
            placeholder = { Text(text = stringResource(R.string.placeholder_input_name_sign_up)) },
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(24.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.label_input_email_sign_in)) },
            placeholder = { Text(text = stringResource(R.string.placeholder_input_email_sign_in)) },
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(24.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.label_input_password_sign_in)) },
            placeholder = { Text(text = stringResource(R.string.placeholder_input_password_sign_in)) },
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(24.dp))
        Button(
            onClick = {
                onSubmit(email, password)
            },
            colors = ButtonDefaults.buttonColors(

            ),
            shape = RoundedCornerShape(
                corner = CornerSize(5.dp)
            ),
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.button_sign_in))
        }
        Spacer(modifier = modifier.height(30.dp))
        TextWithAction(
            labels = listOf(
                AnnotationTextItem.Text(stringResource(R.string.not_have_account)),
                AnnotationTextItem.Button(stringResource(R.string.sign_in_here))
            ),
            onTextClick = {
                if (it == 1) {
                    goToSingIn()
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewScreenSignIn() {
    FileBoxTheme {
        ScreenSignUp()
    }
}