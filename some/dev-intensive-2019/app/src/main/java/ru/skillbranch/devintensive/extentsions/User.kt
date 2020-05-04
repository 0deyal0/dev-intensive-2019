package ru.skillbranch.devintensive.extentsions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.units.Utils

fun User.toUserView(): UserView {

    val nickname = Utils.transliteration("")
    val initials = Utils.toInitials(firstName, lastName)
    val status =
        if (lastVisit == null) "Еще ни разу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit?.humanizeDiff()}"


    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickname,
        avatar = avatar,
        initials = initials,
        status = status
    )
}


