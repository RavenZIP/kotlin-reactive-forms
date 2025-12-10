package data

/** Типы изменений значения в контролах */
sealed class ValueChangeType {
    /** Значение при инициализации */
    data object Initialize : ValueChangeType()

    /** Значение при установке */
    data object Set : ValueChangeType()

    /** Значение при сбросе */
    data object Reset : ValueChangeType()
}

fun ValueChangeType.isInitialize(): Boolean = this is ValueChangeType.Initialize

fun ValueChangeType.isSet(): Boolean = this is ValueChangeType.Set

fun ValueChangeType.isReset(): Boolean = this is ValueChangeType.Reset
