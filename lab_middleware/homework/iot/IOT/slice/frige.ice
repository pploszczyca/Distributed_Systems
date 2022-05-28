#ifndef FRIGE_ICE
#define FRIGE_ICE

module device {
    module fridge {
        enum FreezingLevel { MIN, AVERAGE, MAX };

        exception NoSpace {};
        exception NoProduct {};
        exception BadDateFormat {};

        struct Product {
            string name;
            double size;
            string expirationDate;      // format dd-MM-yyyy
        };

        sequence <Product> Products;

        interface BasicFridge {
            void addProduct(Product product) throws NoSpace, BadDateFormat;
            Product takeProductOutByName(string productName) throws NoProduct;
            Products getProductsIn();
            int numberOfProductsIn();
            void setFreezingLevel(FreezingLevel freezingLevel);
            FreezingLevel getFreezingLevel();
        };
    };
};

#endif