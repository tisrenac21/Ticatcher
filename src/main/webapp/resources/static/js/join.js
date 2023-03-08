
function checkChangeNumber() {
    document.querySelector('#user_hphone_check').value = 'false';
    document.querySelector('#input_hphone_btn').setAttribute('style', '');
  }
  
  function onSendAuthNumber() {
    var phoneNumber = getPhoneNumberFromUserInput();
    var appVerifier = window.recaptchaVerifier;
    try {
      firebase.auth().signInWithPhoneNumber(phoneNumber, appVerifier)
        .then(function (confirmationResult) {
          // SMS sent. Prompt user to type the code from the message, then sign the
          // user in with confirmationResult.confirm(code).
          window.confirmationResult = confirmationResult;
          window.alert('SMS로 인증번호가 전송되었습니다. 인증번호는 Google 서비스로 발송되며 [국제발신] 또는 [국외발신]으로 표기됩니다.');
          document.querySelector('.loading_img_div').setAttribute('style', 'display: none');
          document.querySelector('#user_hphone').classList.add('disableBackGround');
          $('#pdtop_hphone_verify').css('display', '');
          $('#input_hphone_btn').attr('class', 'btn_hphone_off');
          $('#submit_hphone_btn').attr('class', 'btn_hphone_on');
          $('#user_hphone_verify').focus();
        }).catch(function (error) {
          if (error.code === 'auth/invalid-phone-number') {
            alert('휴대폰번호를 정확히 입력해주세요.');
          } else {
            window.alert(error.code);
          }
          // Error; SMS not sent
          document.querySelector('.loading_img_div').setAttribute('style', 'display: none');
        });
    } catch (e) {
      console.log(e);
    }
  }
  
  function getPhoneNumberFromUserInput() {
    var phoneNumber = $('#user_hphone').val();
    var phoneCode = $('#country_code').val();
  
    if (phoneNumber == '' || phoneNumber.length < 10 ) {
      window.alert('휴대폰 번호를 입력해 주세요');
      $('#user_hphone').focus();
      document.querySelector('.loading_img_div').setAttribute('style', 'display: none');
      return;
    } else {
      if (phoneCode === '82' && phoneNumber.length === 12 && phoneNumber.charAt(0) !== '0') phoneNumber = `0${phoneNumber}`;
      return `+${phoneCode}${phoneNumber.replace(/-/gi, '')}`;
    }
  }
  
  $('#input_hphone_btn').click(function() {
    document.querySelector('.loading_img_div').setAttribute('style', 'display: block');
    onSendAuthNumber();
  });
  
  $('#user_hphone').keypress(function (event) {
    if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
      event.preventDefault();
    }
  });
  
  $('#submit_hphone_btn').click(function() {
    document.querySelector('.loading_img_div').setAttribute('style', 'display: block');
    var code = $('#user_hphone_verify').val();
    if (code == '' || code.length != 6) {
      window.alert('문자로 전송받은 인증코드를 정확히 입력해주세요.');
      $('#user_hphone_verify').focus();
      document.querySelector('.loading_img_div').setAttribute('style', 'display: none');
      return;
    } else {
      confirmationResult.confirm(code).then(function (result) {
        // User signed in successfully.
        //var user = result.user;
        var user_hphone = $('#user_hphone').val();
        var dataSet = 'user_hphone=' + user_hphone;
        $.ajax({
          url: '/login/?mode=check_hphone',
          type: 'POST',
          data: dataSet,
        }).success(function(data, status, xhr) {
          if (data != 'OK') {
            window.alert('이미 ['+data+'] 아이디로 가입된 번호입니다. 가입하신 적이 없다면 고객센터(1599-3089) 또는 이메일(cs@timeticket.co.kr)로 문의해주세요.');
            document.querySelector('.loading_img_div').setAttribute('style', 'display: none');
            return false;
          } else {
            window.alert('휴대폰 인증이 완료되었습니다.');
            document.querySelector('.loading_img_div').setAttribute('style', 'display: none');
            document.querySelector('#user_hphone_verify').classList.add('disableBackGround');
            $('#user_hphone_check').val('OK');
            $('#submit_hphone_btn').attr('class', 'btn_hphone_off');
          }
        }).done(function() {
        });
      }).catch(function (error) {
        // User couldn't sign in (bad verification code?)
        window.alert('인증 코드가 일치하지 않습니다. 다시 입력해주세요.');
        $('#input_hphone_btn').attr('class', 'btn_hphone_on');
        $('#submit_hphone_btn').attr('class', 'btn_hphone_off');
        document.querySelector('.loading_img_div').setAttribute('style', 'block');
      });
    }
  });
  
  var checkDupId = false;
  var idRegx = /^[a-z]+[a-z0-9\_\-]{3,19}$/i;
  
  $('#user_id').keyup(function(event) {
    if (!idRegx.test($('#user_id').val())) {
      $('.input_alert_id').css('color', 'red');
      $('.input_alert_id').text('4~20자의 영문, 숫자, 특수기호(_),(-)만 사용할 수 있어요.');
      checkDupId = false;
      return;
    }
    if ($('#isUser').val() == "f" && idRegx.test($('#user_id').val())) {
      var userIdParam = `user_id=${$('#user_id').val()}`;
      $.ajax({
        url: '/login/?mode=check_dupid',
        type: 'POST',
        data: userIdParam,
        cache: false,
      }).done(function(data, status, xhr) {
        if (data == 'OK') {
          $('.input_alert_id').css('color', 'blue');
          $('.input_alert_id').text('사용가능한 아이디입니다.');
          checkDupId = true;
          return;
        } else if (data == 'OUT') {
          $('.input_alert_id').css('color', 'red');
          $('.input_alert_id').text('가입할 수 없는 아이디입니다.');
          checkDupId = false;
          return;
        } else {
          $('.input_alert_id').css('color', 'red');
          $('.input_alert_id').text('이미 가입된 아이디입니다.');
          checkDupId = false;
          return;
        }
      });
    }
  });
  
  $('#user_pass').click(function(event) {
    $('.input_alert_password').css('color', 'red');
    $('.input_alert_password').text('비밀번호는 8자리 이상 영문/숫자/특수문자 조합으로 입력해주세요.');
    return;
  });
  
  $('#user_name').click(function(event) {
    $('.input_alert_name').css('color', 'red');
    $('.input_alert_name').text('이름은 반드시 실명으로 기재해주세요.');
    return;
  });
  
  $('#user_email').click(function(event) {
    $('.input_alert_email').css('color', 'red');
    $('.input_alert_email').text('정확한 이메일 주소를 입력해주세요.');
    return;
  });
  
  $('#user_pass').focusout(function(event) {
    $('.input_alert_password').text('');
    return;
  });
  
  $('#user_name').focusout(function(event) {
    $('.input_alert_name').text('');
    return;
  });
  
  $('#user_email').focusout(function(event) {
    $('.input_alert_email').text('');
    return;
  });
  
  let returnProductURL = location.href.split('&r=')[1];
  if (returnProductURL === undefined) returnProductURL = location.href.split('?r=')[1];
  
  var emailRegx = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/i;
  var nameRegx = /[^a-z|A-Z|ㄱ-ㅎ|가-힣]+$/i;
  var passwordRegx = /^(?=.*[a-zA-Z0-9#?!@$%^*-]).{8,20}$/i;
  
  $('#submitComplete').click(function () {
    if ($("#isUser").val() == "f") {
      if ($("#user_id").val() == "") {
        window.alert('아이디를 입력해주세요.');
        $("#user_id").focus();
        return false;
      }
      if (!idRegx.test($("#user_id").val())) {
        window.alert('아이디는 4~20자의 영문, 숫자, 특수기호(_),(-)만 사용할 수 있어요.');
        $("#user_id").focus();
        return false;
      }
      if (!checkDupId) {
        window.alert('아이디를 다시 확인해주세요.');
        $("#user_id").focus();
        return false;
      }
      if ($("#user_pass").val() == "") {
        window.alert('비밀번호를 입력해주세요.');
        $("#user_pass").focus();
        return false;
      }
      if (!passwordRegx.test($("#user_pass").val())) {
        window.alert('비밀번호는 8자리 이상 20자리 이하의 영문/숫자/특수문자 조합으로 입력해주세요.');
        $("#user_pass").focus();
        return false;
      }
      if(/(\w)\1\1\1/.test($("#user_pass").val())) {
        window.alert('비밀번호에 같은 문자를 4번 이상 사용할 수 없습니다.');
        $("#user_pass").focus();
        return false;
      }
      if (document.querySelector('#user_pass').value.indexOf('&') != '-1') {
        alert('비밀번호에 사용할 수 없는 특수기호가 포함되어 있어요')
        return false;
      }
      if ( $("#user_pass").val().length > 0 && $("#user_pass").val() != $("#user_pass_verify").val()) {
        window.alert('비밀번호가 일치하지 않습니다.');
        $("#user_pass_verify").focus();
        return false;
      }
  
      if ($("#user_name").val() == "" || $('#user_name').val().length < 2) {
        window.alert('이름을 입력해주세요.');
        $("#user_name").focus();
        return false;
      }
      if (nameRegx.test($("#user_name").val())) {
        window.alert('이름은 한글 또는 영문으로만 입력해주세요.');
        $("#user_name").focus();
        return false;
      }
      if ($("#user_email").val() == "" || $('#user_email').val().length < 3) {
        window.alert('이메일 주소를 입력해주세요.');
        $("#user_email").focus();
        return false;
      }
      if (!emailRegx.test($("#user_email").val())) {
        window.alert('이메일 주소를 정확히 입력해주세요.');
        $("#user_email").focus();
        return false;
      }
    }

    if ($("#user_name").val() == "" || $('#user_name').val().length < 2) {
      window.alert('이름을 입력해주세요.');
      $("#user_name").focus();
      return false;
    }
    if (nameRegx.test($("#user_name").val())) {
      window.alert('이름은 한글 또는 영문으로만 입력해주세요.');
      $("#user_name").focus();
      return false;
    }
    if ($("#user_email").val() == "" || $('#user_email').val().length < 3) {
      window.alert('이메일 주소를 입력해주세요.');
      $("#user_email").focus();
      return false;
    }
    if (!emailRegx.test($("#user_email").val())) {
      window.alert('이메일 주소를 정확히 입력해주세요.');
      $("#user_email").focus();
      return false;
    }
  
    // 핸드폰번호 규격화
    let countryCode = $('#country_code').val();
    let phoneNumber = $('#user_hphone').val();
    if ($('#country_code').val() === '82' && phoneNumber.length === 12 && phoneNumber.charAt(0) !== '0') {
      phoneNumber = phoneNumber.replace(/[^0-9]/g, '');
      phoneNumber = `0${phoneNumber}`;
      phoneNumber =  `${phoneNumber.substr(0, 3)}-${phoneNumber.substr(3, 4)}-${phoneNumber.substr(7, 4)}`;
    }
  
    var dataSet = `user_id=${$('#user_id').val()}&user_name=${$('#user_name').val()}&user_email=${$('#user_email').val()}&user_hphone=${phoneNumber}&country_code=${countryCode}&user_prefix=${$('#user_prefix').val()}&user_pass=${$('#user_pass').val()}&update_user=${$('#update_user').val()}&isUser=${$('#isUser').val()}&duinfo=${$('#duinfo').val()}&idKey=${$('#idKey').val()}`;
  
  
    $('#submitComplete').attr('disabled', true);
  
    $.ajax({
      url: '/login/?mode=user_submit',
      type: 'POST',
      data: dataSet,
    }).success(function(data, status, xhr) {
  
    if (data == 'OK') {
      if ($('#isUser').val() == "u" || $('#isUser').val() == "ut") {
        window.alert('회원정보가 수정되었습니다.');
        parent.location.href = '/?mod_ok';
      } else {
        window.alert('회원가입이 완료되었습니다!');
        if (returnProductURL === undefined) {
          parent.location.href = '/?welcome';
        } else {
          returnProductURL = returnProductURL.replace('/&', '/?');
          parent.location.href = returnProductURL;
        }
      }
  
    } else {
          if (data == 'REF_CODE_FAIL') {
              window.alert('초대코드가 유효하지 않아서 포인트가 지급되지 않았습니다.\n초대링크가 정확한지, 또는 초대한 분의 회원탈퇴 여부를 확인해주세요.');
          }
      if (data == 'MEMBER_OUT_HPHONE') {
              window.alert('해당 휴대폰번호로 가입이력이 있어 포인트가 지급되지 않습니다.\n친구초대 포인트는 신규회원에 한해 지급되는 점 양해해주세요.');
          }
      parent.location.href='/';
    }
  
  
    }).done(function() {
      //window.alert('입력 되었습니다.');
      //location.href='/';
      return false;
    });
  
  });
  