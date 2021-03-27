package com.example.pressurepoints;

public class CustomAlertDialog {
    private String Title;
    private String Message;
    private String btnOKTitle;

    public CustomAlertDialog() {
    }

    public void showMessage() {
        //                        AlertDialog.Builder builderAlert = new AlertDialog.Builder(
//                                MainActivity
//                        );
//                        builderAlert.setIcon(R.drawable.ic_check);
//                        builderAlert.setTitle("Login concluído!");
//                        builderAlert.setMessage("Seja bem vindo ao aplicativo ponto de pressão!");
//                        builderAlert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//                        AlertDialog alertDialog = builderAlert.create();
//                        alertDialog.show();
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getBtnOKTitle() {
        return btnOKTitle;
    }

    public void setBtnOKTitle(String btnOKTitle) {
        this.btnOKTitle = btnOKTitle;
    }
}
