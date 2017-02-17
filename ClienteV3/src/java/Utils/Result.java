/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author MAURICIO
 */
public class Result {
    public Object result;
    public int errorCode;
    
    //TIPOS DE OBJETO
    public String resultString;
    public int resultInt;
    public boolean resultBoolean;

    public Result(Object result, int errorCode) {
        this.result = result;
        this.errorCode = errorCode;
    }

    public Result(String resultString, int errorCode) {
        this.errorCode = errorCode;
        this.resultString = resultString;
    }

    public Result(int resultInt, int errorCode) {
        this.errorCode = errorCode;
        this.resultInt = resultInt;
    }

    public Result(boolean resultBoolean, int errorCode) {
        this.errorCode = errorCode;
        this.resultBoolean = resultBoolean;
    }
}
