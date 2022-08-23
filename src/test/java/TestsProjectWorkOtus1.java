import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testsPreconditionsInit.TestsPreconditionsInit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.By.*;

public class TestsProjectWorkOtus1 extends TestsPreconditionsInit {
    @Test
    public void test1CheckLessonsQA(){
        homePage.open();
        //1 Пользователь переходит в разделе тестирование
        act.click(xpath("//div[@id='categories_id']//a[@title='Тестирование']"));
        //2 На странице отображаются карточки курсов.
        List<WebElement> lessons = driver.findElements(By.xpath("//div[@class='lessons']/a"));
        //Количество карточек равно 11
        assertEquals(11,lessons.size());
    }

    @Test
    public void test2CheckLessonCard(){
        homePage.open();
        //1 Пользователь переходит на карточку курса
        act.click(xpath("//div[@id='categories_id']//a[@title='Тестирование']"));
        List<WebElement> lessons = driver.findElements(By.xpath("//div[@class='lessons']/a"));
        //2 В карточке указана информация о курсе:
        //Название
        //Описание
        //Длительность обучения
        //Формат

        //ФАКТИЧЕСКАЯ ИНФОРМАЦИЯ В КАРТОЧКЕ ОТЛИЧАЕТСЯ ОТ ЗАДАНИЯ, ТЕСТ СОСТАВЛЕН С УЧЕТОМ ПРИСУТСТВИЯ ЗАГОЛОВКА, ВРЕМЕНИ НАЧАЛА И ПРОДОЛЖИТЕЛЬНОСТИ
        for (int i=1; i<=(lessons.size()); i++){
        String title = driver.findElement(xpath("//div[@class='lessons']/a["+ i +"]/div/div[3]")).getText();
        String start = driver.findElement(xpath("//div[@class='lessons']/a["+ i +"]//div[@class='lessons__new-item-start']")).getText();
        String time = driver.findElement(xpath("//div[@class='lessons']/a["+ i +"]//div[@class='lessons__new-item-time']")).getText();
        assertNotNull(title);
        assertNotNull(start);
        assertNotNull(time);
        assertNotEquals("",title);
        assertNotEquals("",start);
        assertNotEquals("",time);
        }
    }

    @Test
    public void test3DateValidation() throws InterruptedException {
        homePage.open();
        //1 Пользователь переходит в раздел События -> Календарь мероприятий
        act.moveCursor(xpath("//p[text()='События']/.."));
        act.click(xpath("//a[@title='Календарь мероприятий']"));
        //ЗДЕСЬ НЕОБХОДИМ СКРОЛЛ ВНИЗ СТРАНИЦЫ С ДОСКРОЛЛОМ
        act.scrollDownWithAJAX();
        //2 На странице отображаются карточки предстоящих мероприятий.
        List<WebElement> events = driver.findElements(By.xpath("//a[@class='dod_new-event']"));
        String[] dateEvent = new String[events.size()];
        String[] timeEvent = new String[events.size()];
        for (int i=0; (events.size())>i; i++){
            dateEvent[i] = driver.findElement(xpath("//a[" + (i+1) + "]//span[1]/span[@class='dod_new-event__date-text']")).getText();
            timeEvent[i] = driver.findElement(xpath("//a[" + (i+1) + "]//span[2]/span[@class='dod_new-event__date-text']")).getText();
        }/*3 Даты проведения мероприятий больше или равны текущей дате
        Написать метод, который бы валидировал ТЕКСТ формата "13 сентября" с текущей датой с нуля уже не успею, слишком много логики необходимо
        кроме того, у указанной даты не отмечен год, потому не понятно - до какого месяца дату считать будущей или прошедшейж
        */
    }

    @Test
    public void test4DateValidation() throws InterruptedException {
        homePage.open();
        //1 Пользователь переходит в раздел События -> Календарь мероприятий
        act.moveCursor(xpath("//p[text()='События']/.."));
        act.click(xpath("//a[@title='Календарь мероприятий']"));
        //2 Пользователь сортирует мероприятия по типу Открытые вебинары
        act.click(xpath("//span[text()='Все мероприятия']"));
        act.click(xpath("//a[text() = 'Открытый вебинар']"));
        //ЗДЕСЬ НЕОБХОДИМ СКРОЛЛ ВНИЗ СЬРАНИЦЫ С ДОСКРОЛЛОМ
        act.scrollDownWithAJAX();
        List<WebElement> events = driver.findElements(By.xpath("//a[@class='dod_new-event']"));
        for (int i = 0; i < events.size(); i++) {
            /*3 На странице отображаются карточки предстоящих мероприятий. На каждой карточке в типе указанно "День открытых дверей"
            Никаких "Дней открытых дверей" на карточках нет.
            Проверка производилась по тексту "Открытый вебинар", тем самым произведена проверка работы фильтра
             */
            assertEquals("Открытый вебинар", driver.findElement(xpath("//div[@class='dod_new-type__text']")).getText());
        }
    }


}