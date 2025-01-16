export const environment = {
    production : false,

    jsonEqual: function (a: any, b: any) {
        return JSON.stringify(a) === JSON.stringify(b);
    },

    SeleccionarObjArray: function (lista: Array<object>, objE: Array<object>) {
        var res = new Array();
        objE.forEach(ele => {
            lista.forEach(valor => {
                if (environment.jsonEqual(valor, ele))
                    res.push(valor);
            });
        });
        return res;
    },

    checkIfTokenExists : function (){
        let token = sessionStorage.getItem("token");
        
        return token == "";
    }
}