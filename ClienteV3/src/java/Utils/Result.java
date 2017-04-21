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

    public Result(Object result, int errorCode) {
        this.result = result;
        this.errorCode = errorCode;
    }
}
