$(document).ready(function() {
    let currentYear = new Date().getFullYear();
    let currentMonth = new Date().getMonth();
    let selectedDay = null;

    function generateCalendar(year, month) {
        const firstDay = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();

        let calendarHTML = '<div class="calendar-header">';
        calendarHTML += `<button id="prev-month">이전</button>`;
        calendarHTML += `<span class="month-display">${year}년 ${month + 1}월</span>`;
        calendarHTML += `<button id="next-month">다음</button>`;
        calendarHTML += '</div>';

        calendarHTML += '<table class="calendar">';
        calendarHTML += '<thead>';
        calendarHTML += '<tr><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th><th>Sun</th></tr>';
        calendarHTML += '</thead>';
        calendarHTML += '<tbody>';

        let date = 1;
        for (let i = 0; i < 6; i++) {
            calendarHTML += '<tr>';
            for (let j = 0; j < 7; j++) {
                if (i === 0 && j < (firstDay === 0 ? 6 : firstDay - 1)) {
                    calendarHTML += '<td></td>';
                } else if (date > lastDate) {
                    calendarHTML += '<td></td>';
                } else {
                    let additionalClass = (selectedDay === `${year}-${month + 1}-${date}`) ? ' selected-day' : '';
                    calendarHTML += `<td class="calendar-day${additionalClass}" data-date="${year}-${month + 1}-${date}">${date}</td>`;
                    date++;
                }
            }
            calendarHTML += '</tr>';
        }
        calendarHTML += '</tbody>';
        calendarHTML += '</table>';

        return calendarHTML;
    }

    function renderCalendar(year, month) {
        const calendar = generateCalendar(year, month);
        $('#calendar-container').html(calendar);

        // 각 셀의 높이를 동적으로 계산하여 설정
        const containerHeight = $('#calendar-container').height() - $('.calendar-header').outerHeight();
        const cellHeight = containerHeight / 7; // 헤더 공간을 확보하기 위해 셀 높이 조정

        $('.calendar td, .calendar th').css({
            'height': cellHeight + 'px',
            'width': cellHeight + 'px'  // 높이와 너비를 동일하게 설정하여 항상 정사각형 유지
        });
    }

    function updateCalendar(offset) {
        currentMonth += offset;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        } else if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        renderCalendar(currentYear, currentMonth);
    }

    $(document).on('click', '#prev-month', function() {
        updateCalendar(-1);
    });

    $(document).on('click', '#next-month', function() {
        updateCalendar(1);
    });

    $(document).on('click', '.calendar-day', function() {
        selectedDay = $(this).data('date');
        renderCalendar(currentYear, currentMonth);
    });

    renderCalendar(currentYear, currentMonth);
});


