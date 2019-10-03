package cs478.project2.model;

import java.util.Arrays;
import java.util.List;

import cs478.project2.R;

public class PhoneDatabase {

    public final static Phone IPHONE_11 =
            new Phone("Apple", "iPhone 11", "6.1 inches", "$699-$849",
                    R.drawable.small_iphone_11, R.drawable.iphone_11, "https://www.apple.com/iphone-11/",
                    Arrays.asList(
                            new PhoneSpec("Dimensions", "150.9 x 75.7 x 8.3 mm (5.94 x 2.98 x 0.33 in)"),
                            new PhoneSpec("Weight", "194 g (6.84 oz)"),
                            new PhoneSpec("Screen technology", "Liquid Retina IPS LCD capacitive touchscreen"),
                            new PhoneSpec("Screen resolution", "828 x 1792 pixels"),
                            new PhoneSpec("Pixel density", "326 ppi"),
                            new PhoneSpec("Storage capacity", "64 GB, 128 GB, 256 GB"),
                            new PhoneSpec("SoC", "Apple A13 Bionic"),
                            new PhoneSpec("RAM", "4 GB"),
                            new PhoneSpec("Main camera", "Dual 12 MP, f/1.8, 26mm (wide) + 12 MP, f/2.4, 13mm (ultrawide)"),
                            new PhoneSpec("Front camera", "12 MP, f/2.2, 23mm (wide)"),
                            new PhoneSpec("Battery capacity", "3,110 mAh")));

    public final static Phone IPHONE_11_PRO =
            new Phone("Apple", "iPhone 11 Pro", "5.8 inches", "$999-$1349",
                    R.drawable.small_iphone_11_pro, R.drawable.iphone_11_pro, "https://www.apple.com/iphone-11-pro/",
                    Arrays.asList(
                            new PhoneSpec("Dimensions", "144 x 71.4 x 8.1 mm (5.67 x 2.81 x 0.32 in)"),
                            new PhoneSpec("Weight", "188 g (6.63 oz)"),
                            new PhoneSpec("Screen technology", "Super Retina XDR OLED capacitive touchscreen"),
                            new PhoneSpec("Screen resolution", "1125 x 2436 pixels"),
                            new PhoneSpec("Pixel density", "458 ppi"),
                            new PhoneSpec("Storage capacity", "64 GB, 128 GB, 256 GB"),
                            new PhoneSpec("SoC", "Apple A13 Bionic"),
                            new PhoneSpec("RAM", "4 GB"),
                            new PhoneSpec("Main camera", "Triple 12 MP, f/1.8, 26mm (wide) + 12 MP, f/2.0, 52mm (telephoto) + 12 MP, f/2.4, 13mm (ultrawide)"),
                            new PhoneSpec("Front camera", "12 MP, f/2.2, 23mm (wide)"),
                            new PhoneSpec("Battery capacity", "3,046 mAh")));

    public final static Phone IPHONE_11_PRO_MAX =
            new Phone("Apple", "iPhone 11 Pro Max", "6.5 inches", "$1099-$1449",
                    R.drawable.small_iphone_11_pro_max, R.drawable.iphone_11_pro_max, "https://www.apple.com/iphone-11-pro/",
                    Arrays.asList(
                            new PhoneSpec("Dimensions", "158 x 77.8 x 8.1 mm (6.22 x 3.06 x 0.32 in)"),
                            new PhoneSpec("Weight", "226 g (7.97 oz)"),
                            new PhoneSpec("Screen technology", "Liquid Retina IPS LCD capacitive touchscreen"),
                            new PhoneSpec("Screen resolution", "1242 x 2688"),
                            new PhoneSpec("Pixel density", "458 ppi"),
                            new PhoneSpec("Storage capacity", "64 GB, 128 GB, 256 GB"),
                            new PhoneSpec("SoC", "Apple A13 Bionic"),
                            new PhoneSpec("RAM", "4 GB"),
                            new PhoneSpec("Main camera", "Triple 12 MP, f/1.8, 26mm (wide) + 12 MP, f/2.0, 52mm (telephoto) + 12 MP, f/2.4, 13mm (ultrawide)"),
                            new PhoneSpec("Front camera", "12 MP, f/2.2, 23mm (wide)"),
                            new PhoneSpec("Battery capacity", "3,969 mAh")));

    public final static Phone PIXEL_3 =
            new Phone("Google", "Pixel 3", "5.5 inches", "$799-$899",
                    R.drawable.small_pixel_3, R.drawable.pixel_3, "https://store.google.com/product/pixel_3",
                    Arrays.asList(
                            new PhoneSpec("Dimensions", "145.6 x 68.2 x 7.9 mm (5.73 x 2.69 x 0.31 in)"),
                            new PhoneSpec("Weight", "148 g (5.22 oz)"),
                            new PhoneSpec("Screen technology", "P-OLED capacitive touchscreen"),
                            new PhoneSpec("Screen resolution", "1080 x 2160 pixels"),
                            new PhoneSpec("Pixel density", "443 ppi"),
                            new PhoneSpec("Storage capacity", "64 GB, 128 GB"),
                            new PhoneSpec("SoC", "Qualcomm Snapdragon 845"),
                            new PhoneSpec("RAM", "4 GB"),
                            new PhoneSpec("Main camera", "12.2 MP, f/1.8, 28mm (wide)"),
                            new PhoneSpec("Front camera", "Dual 8 MP, f/1.8, 28mm (wide) + 8 MP, f/2.2, 19mm (ultrawide)"),
                            new PhoneSpec("Battery capacity", "2915 mAh")));

    public final static Phone PIXEL_3_XL =
            new Phone("Google", "Pixel 3 XL", "6.3 inches", "$899-$999",
                    R.drawable.small_pixel_3_xl, R.drawable.pixel_3_xl, "https://store.google.com/product/pixel_3",
                    Arrays.asList(
                            new PhoneSpec("Dimensions", "158 x 76.7 x 7.9 mm (6.22 x 3.02 x 0.31 in)"),
                            new PhoneSpec("Weight", "184 g (6.49 oz)"),
                            new PhoneSpec("Screen technology", "P-OLED capacitive touchscreen"),
                            new PhoneSpec("Screen resolution", "1440 x 2960 pixels"),
                            new PhoneSpec("Pixel density", "523 ppi"),
                            new PhoneSpec("Storage capacity", "64 GB, 128 GB"),
                            new PhoneSpec("SoC", "Qualcomm Snapdragon 845"),
                            new PhoneSpec("RAM", "4 GB"),
                            new PhoneSpec("Main camera", "12.2 MP, f/1.8, 28mm (wide)"),
                            new PhoneSpec("Front camera", "Dual 8 MP, f/1.8, 28mm (wide) + 8 MP, f/2.2, 19mm (ultrawide)"),
                            new PhoneSpec("Battery capacity", "3430 mAh")));

    public final static Phone PIXEL_3A =
            new Phone("Google", "Pixel 3a", "5.5 inches", "$399",
                    R.drawable.small_pixel_3a, R.drawable.pixel_3a, "https://store.google.com/product/pixel_3",
                    Arrays.asList(
                            new PhoneSpec("Dimensions", "151.3 x 70.1 x 8.2 mm (5.96 x 2.76 x 0.32 in)"),
                            new PhoneSpec("Weight", "147 g (5.19 oz)"),
                            new PhoneSpec("Screen technology", "OLED capacitive touchscreen"),
                            new PhoneSpec("Screen resolution", "1080 x 2220 pixels"),
                            new PhoneSpec("Pixel density", "441 ppi"),
                            new PhoneSpec("Storage capacity", "64 GB"),
                            new PhoneSpec("SoC", "Qualcomm Snapdragon 670"),
                            new PhoneSpec("RAM", "4 GB"),
                            new PhoneSpec("Main camera", "12.2 MP, f/1.8, 28mm (wide)"),
                            new PhoneSpec("Front camera", "8 MP, f/2.0, 24mm (wide)"),
                            new PhoneSpec("Battery capacity", "3000 mAh")));

    public final static Phone PIXEL_3A_XL =
            new Phone("Google", "Pixel 3a XL", "6.3 inches", "$479",
                    R.drawable.small_pixel_3a_xl, R.drawable.pixel_3a_xl, "https://store.google.com/product/pixel_3",
                    Arrays.asList(
                            new PhoneSpec("Dimensions", "160.1 x 76.1 x 8.2 mm (6.30 x 3.00 x 0.32 in)"),
                            new PhoneSpec("Weight", "167 g (5.89 oz)"),
                            new PhoneSpec("Screen technology", "OLED capacitive touchscreen"),
                            new PhoneSpec("Screen resolution", "1080 x 2160 pixels"),
                            new PhoneSpec("Pixel density", "402 ppi"),
                            new PhoneSpec("Storage capacity", "64 GB"),
                            new PhoneSpec("SoC", "Qualcomm Snapdragon 670"),
                            new PhoneSpec("RAM", "4 GB"),
                            new PhoneSpec("Main camera", "12.2 MP, f/1.8, 28mm (wide)"),
                            new PhoneSpec("Front camera", "8 MP, f/2.0, 24mm (wide)"),
                            new PhoneSpec("Battery capacity", "3700 mAh")));

    public final static Phone GALAXY_S10E =
            new Phone("Samsung", "Galaxy S10e", "5.8 inches", "$749-$849",
                    R.drawable.small_galaxy_s10e, R.drawable.galaxy_s10e, "https://www.samsung.com/us/mobile/galaxy-s10/",
                    Arrays.asList(
                            new PhoneSpec("Dimensions", "142.2 x 69.9 x 7.9 mm (5.60 x 2.75 x 0.31 in)"),
                            new PhoneSpec("Weight", "150 g (5.29 oz)"),
                            new PhoneSpec("Screen technology", "Dynamic AMOLED capacitive touchscreen"),
                            new PhoneSpec("Screen resolution", "1080 x 2280 pixels"),
                            new PhoneSpec("Pixel density", "438 ppi"),
                            new PhoneSpec("Storage capacity", "128 GB, 256 GB"),
                            new PhoneSpec("SoC", "Samsung Exynos 9820"),
                            new PhoneSpec("RAM", "6 GB, 8 GB"),
                            new PhoneSpec("Main camera", "Dual 12 MP, f/1.5-2.4, 26mm (wide) + 16 MP, f/2.2, 12mm (ultrawide)"),
                            new PhoneSpec("Front camera", "10 MP, f/1.9, 26mm (wide)"),
                            new PhoneSpec("Battery capacity", "3100 mAh")));

    public final static Phone GALAXY_S10 =
            new Phone("Samsung", "Galaxy S10", "6.1 inches", "$899-$1149",
                    R.drawable.small_galaxy_s10, R.drawable.galaxy_s10, "https://www.samsung.com/us/mobile/galaxy-s10/",
                    Arrays.asList(
                            new PhoneSpec("Dimensions", "149.9 x 70.4 x 7.8 mm (5.90 x 2.77 x 0.31 in)"),
                            new PhoneSpec("Weight", "157 g (5.54 oz)"),
                            new PhoneSpec("Screen technology", "Dynamic AMOLED capacitive touchscreen"),
                            new PhoneSpec("Screen resolution", "1440 x 3040 pixels"),
                            new PhoneSpec("Pixel density", "550 ppi"),
                            new PhoneSpec("Storage capacity", "128 GB, 512 GB"),
                            new PhoneSpec("SoC", "Semsung Exynos 9820"),
                            new PhoneSpec("RAM", "8 GB"),
                            new PhoneSpec("Main camera", "Triple 12 MP, f/1.5-2.4, 26mm (wide) + 12 MP, f/2.4, 52mm (telephoto) + 16 MP, f/2.2, 12mm (ultrawide)"),
                            new PhoneSpec("Front camera", "10 MP, f/1.9, 26mm (wide)"),
                            new PhoneSpec("Battery capacity", "3400 mAh")));

    public final static Phone GALAXY_S10PLUS =
            new Phone("Samsung", "Galaxy S10+", "6.4 inches", "$999-$1249",
                    R.drawable.small_galaxy_s10_plus, R.drawable.galaxy_s10_plus, "https://www.samsung.com/us/mobile/galaxy-s10/",
                    Arrays.asList(
                            new PhoneSpec("Dimensions", "157.6 x 74.1 x 7.8 mm (6.20 x 2.92 x 0.31 in)"),
                            new PhoneSpec("Weight", "175 g (6.17 oz)"),
                            new PhoneSpec("Screen technology", "Dynamic AMOLED capacitive touchscreen"),
                            new PhoneSpec("Screen resolution", "1440 x 3040 pixels"),
                            new PhoneSpec("Pixel density", "522 ppi"),
                            new PhoneSpec("Storage capacity", "128 GB, 512 GB"),
                            new PhoneSpec("SoC", "Samsung Exynos 9820"),
                            new PhoneSpec("RAM", "8 GB"),
                            new PhoneSpec("Main camera", "Triple 12 MP, f/1.5-2.4, 26mm (wide) + 12 MP, f/2.4, 52mm (telephoto) + 16 MP, f/2.2, 12mm (ultrawide)"),
                            new PhoneSpec("Front camera", "Dual 10 MP, f/1.9, 26mm (wide) + 8 MP, f/2.2, 22mm (wide)"),
                            new PhoneSpec("Battery capacity", "3700 mAh")));

    public final static List<Phone> ALL_PHONES = Arrays.asList(
            IPHONE_11, IPHONE_11_PRO, IPHONE_11_PRO_MAX,
            PIXEL_3, PIXEL_3_XL, PIXEL_3A, PIXEL_3A_XL,
            GALAXY_S10E, GALAXY_S10, GALAXY_S10PLUS
    );

    private PhoneDatabase() {}

}
