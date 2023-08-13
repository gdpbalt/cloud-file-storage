package im.getmansky.cloudfilestorage.validation

import im.getmansky.cloudfilestorage.model.dto.request.UserRegistrationDto
import org.springframework.beans.BeanWrapperImpl
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class PasswordRepeatValidator : ConstraintValidator<PasswordRepeatValidation, UserRegistrationDto> {
    private var field: String? = null
    private var fieldMatch: String? = null

    override fun initialize(constraintAnnotation: PasswordRepeatValidation?) {
        this.field = constraintAnnotation?.field
        this.fieldMatch = constraintAnnotation?.fieldMatch
    }

    override fun isValid(userRegistrationDto: UserRegistrationDto, context: ConstraintValidatorContext?): Boolean {
        if (field == null || fieldMatch == null) {
            return false
        }
        val fieldValue = BeanWrapperImpl(userRegistrationDto).getPropertyValue(field!!)
        val fieldMatchValue = BeanWrapperImpl(userRegistrationDto).getPropertyValue(fieldMatch!!)

        return fieldValue != null && fieldValue == fieldMatchValue
    }
}