$(function(){
    $(".datepicker").datepicker({
        minDate:0,
        dateFormat: 'yy-mm-dd',
        prevText: '이전달',
        nextText: '다음달',
        currentText: "오늘",
        weekHeader: "주",
        firstDay: 0,
        monthNames: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
        monthNamesShort: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        isRTL: false,
        showMonthAfterYear: true,
        buttonImageOnly: true
    });
    $('.datepicker').datepicker('setDate','today');
});


// $.datepicker.setDefaults({
//     closeText: "닫기",
//     prevText: "이전달",
//     nextText: "다음달",
//     currentText: "오늘",
//     monthNames: ["1월", "2월", "3월", "4월", "5월", "6월",
//         "7월", "8월", "9월", "10월", "11월", "12월"
//     ],
//     monthNamesShort: ["1월", "2월", "3월", "4월", "5월", "6월",
//         "7월", "8월", "9월", "10월", "11월", "12월"
//     ],
//     dayNames: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
//     dayNamesShort: ["일", "월", "화", "수", "목", "금", "토"],
//     dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"],
//     weekHeader: "주",
//     dateFormat: "yy.m.d", // 날짜형태 예)yy년 m월 d일
//     firstDay: 0,
//     isRTL: false,
//     showMonthAfterYear: true,
//     yearSuffix: "년"
// })

// $(".datepicker").datepicker({
//     minDate: 0
// })