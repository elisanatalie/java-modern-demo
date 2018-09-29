package code.modern.datetime;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class DateTimeExample {

    public static void main(final String[] args) {
        LocalDate date = LocalDate.of(2014, 3, 18);
        final LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        final Instant instant = Instant.now();

        // Temporal adjuster
        date = date.with(new NextWorkingDay());
        System.out.println(date);

        final TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(temporal -> {
            final DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        date = date.with(nextWorkingDay);

        System.out.println(date);

        //Formatter
        final DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        final String formattedDate = date.format(italianFormatter);

        System.out.println(formattedDate);

        //Time zone
        final ZoneId romeZone = ZoneId.of("Europe/Rome");
        final ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        final ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        final ZonedDateTime zdt3 = instant.atZone(romeZone);

        System.out.println(zdt1);
        System.out.println(zdt2);
        System.out.println(zdt3);

        // Non standard calendars
        final JapaneseDate japaneseDate = JapaneseDate.from(date);
        final Chronology japaneseChronology = Chronology.ofLocale(Locale.JAPAN);
        final ChronoLocalDate now = japaneseChronology.dateNow();
        System.out.println(japaneseDate);
        System.out.println(now);

        final HijrahDate ramadanDate = HijrahDate.now()
                                                 .with(ChronoField.DAY_OF_MONTH, 1)
                                                 .with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println("Ramadan starts on " + IsoChronology.INSTANCE.date(ramadanDate) + " and ends on " + IsoChronology.INSTANCE.date(
                ramadanDate.with(TemporalAdjusters.lastDayOfMonth())));
    }
}
