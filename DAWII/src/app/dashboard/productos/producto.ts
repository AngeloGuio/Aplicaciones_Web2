export interface Producto {
    idproducto: number;
    tipoproducto: {
        idtipopro: number,
        desctipopro: string
    },
    proveedor: {
        idproveedor: number;
        empresa: string
    }
    nombre: string;
    cantidad: number;
    preciopublico: number;
    stockminimo: number;
    stockmaximo: number;
    estado: {
        codestado: number;
        nombreestado: string
    }
    animal: {
        idanimal: number;
        descanimal: string
    }
    precioproveedor: number;
}