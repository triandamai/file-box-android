package app.trian.filebox.feature.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.filebox.BaseContainer
import app.trian.filebox.R
import app.trian.filebox.components.AnnotationTextItem
import app.trian.filebox.components.TextWithAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ScreenSignIn(
    modifier: Modifier = Modifier,
    goToSignUp: () -> Unit = {},
    onSubmit: (String, String) -> Unit = { _, _ -> }
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
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
                AnnotationTextItem.Text(stringResource(R.string.already_have_account)),
                AnnotationTextItem.Button(stringResource(R.string.create_account)),
            ),
            onTextClick = {
                if (it == 1) {
                    goToSignUp()
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseContainer {
        ScreenSignIn()
    }
}