package im.getmansky.cloudfilestorage.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@Constraint(validatedBy = [PasswordRepeatValidator::class])
@Target(AnnotationTarget.CLASS)
@Retention(
    AnnotationRetention.RUNTIME
)

annotation class PasswordRepeatValidation(
    val message: String = "Passwords don't match!",
    val field: String,
    val fieldMatch: String,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Any>> = []
)
