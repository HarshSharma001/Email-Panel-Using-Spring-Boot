<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="Stylesheet.css">
<title>Email Panel Homepage</title>
</head>
<body>
<form method="post">
&nbsp&nbsp&nbsp&nbsp&nbsp Enter Your Email : <input type="text" name="sender" placeholder="harsh@gmail.com"><br><br>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Enter Password : <input type="Password" name="pass" placeholder="********"><br><br>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Recipient Type : <select id="recipient" name="recipient">
<option>- Select your recipient type -</option>
<option>To</option>	
<option>CC</option>	
<option>BCC</option>	
</select> <br><br>
Enter Receiver's Email : <input type="text" name="receiver" placeholder="sharma@gmail.com"><br><br>
&nbsp&nbsp Enter Email Subject : <input type="text" name="subject" placeholder="Ex: Java Development Internship"><br><br>
<textarea name="typedmessage" placeholder="Type your message here..!"></textarea><br><br>
<label for="attachments">Add Attachments :</label>
<input type="file" id="attachfiles" name="attachfiles" multiple="multiple">
<input type="submit" id="sendmail" name="sendmail" value="Send Email" onclick="form.action='sendemail';">
<label for="recipients">Add Recipients :</label>
<input type="file" id="addrecipient" name="addrecipient" value="Add Recipients">
</form>	
</body>
</html>