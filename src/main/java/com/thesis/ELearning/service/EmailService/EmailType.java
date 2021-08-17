package com.thesis.ELearning.service.EmailService;

/**
 * @Author Kurt Orioque
 */

public class EmailType {

    public static String userVerify(String token){
        return "<!doctype html>\n" +
                "<html lang=\"en\"\n" +
                "      dir=\"ltr\"\n" +
                "      xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "    <title>Email Verify</title>\n" +
                "    <meta name=\"description\" content=\"Email Verify\">\n" +
                "    <style type=\"text/css\">\n" +
                "        a:hover {text-decoration: underline !important;}\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                "<!--100% body table-->\n" +
                "<table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                "       style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                "                   align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                <tr>\n" +
                "                    <td style=\"display: flex;justify-content: center;\">\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                               style=\"max-width:670px;background:#fff;margin-top:30px; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                "                            <tr>\n" +
                "                                <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td style=\"padding:0 35px;\">\n" +
                "                                    <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">  Welcome to Gardner College</h1>\n" +
                "                                    <span\n" +
                "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100%;\"></span>\n" +
                "                                    <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
                "\n" +
                "\n" +
                "                                        Thanks for choosing Gardner! Before we get started, we just need to confirm that this is you.\n" +
                "                                        <br/>Click below to verify your email address:\n" +
                "                                    </p>\n" +
                "                                    <a href=\"https://eellearning.herokuapp.com/verify?access="+token+"\"\n" +
                "                                       style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">Verify\n" +
                "                                        Now</a>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td style=\"height:40px;\">&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                <tr>\n" +
                "                    <td style=\"height:20px;\">&nbsp;</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td style=\"text-align:center;\">\n" +
                "                        <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>https://admiring-goldstine-7f4e6f.netlify.app</strong></p>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td style=\"height:80px;\">&nbsp;</td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<!--/100% body table-->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String passwordReset(String token){
        return "<!doctype html>\n" +
                "<html lang=\"en\"\n" +
                "      dir=\"ltr\"\n" +
                "      xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "\n" +
                "<head>\n" +
                "  <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "  <title>Reset Password Email Template</title>\n" +
                "  <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
                "  <style type=\"text/css\">\n" +
                "    a:hover {text-decoration: underline !important;}\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                "<!--100% body table-->\n" +
                "<table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                "       style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                "  <tr>\n" +
                "    <td>\n" +
                "      <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                "             align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "        <tr>\n" +
                "          <td style=\"text-align:center;margin: auto;display: flex;justify-content: center;\">\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   style=\"max-width:670px;background:#fff;margin-top:30px; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                "              <tr>\n" +
                "                <td style=\"height:40px;\">&nbsp;</td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td style=\"padding:0 35px;\">\n" +
                "                  <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have\n" +
                "                    requested to reset your password</h1>\n" +
                "                  <span\n" +
                "                          style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
                "                  <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
                "                    You have requested that your password be reset. If you did not make this request, simply ignore this email. Your password must be reset within 15 minutes of receipt of this email.\n" +
                "                    To reset your password , simply click the button below.\n" +
                "                  </p>\n" +
                "                   <a href=\"https://eellearning.herokuapp.com/reset-password?access="+token+"\"\n" +
                "                     style=\"background:#20e277;text-decoration:none !important; font-weight:500; margin-top:35px; color:#fff;text-transform:uppercase; font-size:14px;padding:10px 24px;display:inline-block;border-radius:50px;\">Reset\n" +
                "                    Password</a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td style=\"height:40px;\">&nbsp;</td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        <tr>\n" +
                "          <td style=\"height:20px;\">&nbsp;</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td style=\"text-align:center;\">\n" +
                "            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>https://admiring-goldstine-7f4e6f.netlify.app</strong></p>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td style=\"height:80px;\">&nbsp;</td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<!--/100% body table-->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}
