package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extentsions.*
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.units.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "John", "Week")
        val user3 = User("3", "John", "Silverhand", null, lastVisit = Date(), isOnline = true)

        user.printMe()
        user2.printMe()
        user3.printMe()
    }

    @Test
    fun test_factory() {
        val user = User.makeUser("John Wick")
/*        User.makeUser("John Wick")
        User.makeUser("John SilverHand")*/
        val user1 = user.copy(id = "2", lastName = "Cena", lastVisit = Date())

        print("$user \n$user1")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")

        fun getUserInfo() = user
        val (id, firstName, lastName) = getUserInfo()
    }

    @Test
    fun test_dataMaping() {
        val user = User.makeUser("John Wick")
        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Евгений Петросян")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text Message")
        val imgMessage =
            BaseMessage.makeMessage(user, Chat("0"), type = "Image", payload = "any image url")

        println(textMessage.formatMessage())
        println(imgMessage.formatMessage())
    }

    @Test
    fun test_parse_full_name() {
        println(Utils.parseFullName(null)) //null null
        println(Utils.parseFullName("")) //null null
        println(Utils.parseFullName(" ")) //null null
        println(Utils.parseFullName("John")) //John null
    }

    @Test
    fun test_initials() {
        println(Utils.toInitials("john", "doe")) //JD
        println(Utils.toInitials("John", null)) //J
        println(Utils.toInitials(null, null)) //null
        println(Utils.toInitials(" ", "")) //null
    }

    @Test
    fun test_translite() {
        println(Utils.transliteration("Женя Стереотипов")) //Zhenya Stereotipov
        println(Utils.transliteration("Amazing Петр", "_")) //Amazing_Petr)
    }

    @Test
    fun test_humanize_diff() {
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff())  //через 2 минуты
        println(Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(400, TimeUnits.DAY).humanizeDiff()) //более чем через год
    }

    @Test
    fun test_user_builder() {
        val s = "1"
        val n = 2
        val d = Date()
        val b = false
        print(
            User.Builder().id(s)
                .firstName(s)
                .lastName(s)
                .avatar(s)
                .rating(n)
                .respect(n)
                .lastVisit(d)
                .isOnline(b)
                .build()
        )
    }

    @Test
    fun test_plural() {
        println(TimeUnits.SECOND.plural(1))//1 секунду
        println(TimeUnits.MINUTE.plural(4))//4 минуты
        println(TimeUnits.HOUR.plural(19))//19 часов
        println(TimeUnits.DAY.plural(222))//222 дня
    }

    @Test
    fun test_truncate() {
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate())//Bender Bending R...
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15))//Bender Bending...
        println("A     ".truncate(3))//A

    }

    @Test
    fun test_strip_html() {
        println("<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml()) //Образовательное IT-сообщество Skill Branch

    }
}
