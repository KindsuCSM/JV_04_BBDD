package controller;

public class CtrlFrmPrincipal {

    private CtrlFrmPrincipal principal;
    private CtrlFrmPrincipal(){

    }
    public CtrlFrmPrincipal getInstance(){
        if(principal == null){
            principal = new CtrlFrmPrincipal();
        };
        return principal;
    }

}
