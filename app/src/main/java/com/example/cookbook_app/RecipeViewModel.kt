    package com.example.cookbook_app

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.launch
    import androidx.lifecycle.LiveData

    class RecipeViewModel(private val recipeDao: RecipeDao) : ViewModel() {

        val allRecipes: LiveData<List<RecipeEntity>> = recipeDao.getAllRecipesLive()

        fun insertSampleRecipes() {
            viewModelScope.launch(Dispatchers.IO) {
                val recipeCount = recipeDao.getRecipeCount()
                if (recipeCount == 0) {
                    val sampleRecipes = listOf(
                        RecipeEntity(
                            id = 1,
                            name = "Pizza neapolitańska",
                            description = "Danie pochodzące z Neapolu, pokryte sosem pomidorowym, mozzarellą, świeżą bazylią i oliwą z oliwek.",
                            ingredients = "mąka włoska, woda, drożdże, sól, oliwa, pomidory, mozzarella",
                            instructions = "Przygotuj ciasto na pizzę. Połącz ze sobą mąkę i sól. W szklance wymieszaj ciepłą wodę z drożdżami i odstaw na kilka minut, żeby składniki zdążyły się ze sobą połączyć. Następnie stopniowo dodawaj drożdżowy zaczyn do mąki i wyrabiaj ciasto. W międzyczasie dodaj też olej. Ciasto zagniataj do momentu aż stanie się jednolite i sprężyste. Gotową kulę ciasta przelej wodą i odstaw na 10 minut do lodówki. Po upływie tego czasu wyciąg ciasto i odstaw w ciepłe miejsce do wyrośnięcia na kolejnych 20 minut. Dodaj składniki na pizzę. Na placku ułóż plastry pomidora na przemian z plastrami mozzarelli i posyp rozkruszoną mini kostką. Piecz w nagrzanym do 200°C piekarniku przez około 15 minut. Udekoruj listkami bazylii.",
                            image = "pizza_nea",
                            type = "dania główne"

                        ),
                        RecipeEntity(
                            id = 2,
                            name = "Lasagna",
                            description = "Rodzaj makaronu w postaci dużych, prostokątnych płatów, a także typowo włoskie danie przygotowywane na bazie tego makaronu",
                            ingredients = "makaron lasagna, mięso mielone, sos pomidorowy, ser",
                            instructions = "Mielone mięso podsmaż w małej ilości tłuszczu, dodaj koncentrat pomidorowy, posiekane oregano, wodę, sos. Gotuj około 5 minut. Naczynie żaroodporne wysmaruj olejem. Na dno naczynia wylej trochę mięsa z sosem. Na mięso ułóż pierwsza warstwę surowego makaronu lasagne, na makaron znowu nałóż sos z mięsem, po czym sos zakryj makaronem, czynność powtórz jeszcze dwukrotnie. Wierzch posyp warstwą startego sera i całość szczelnie zakryj folią aluminiową. Piecz w nagrzanym do 200 °C piekarniku przez 30 minut. Aby ser był zapieczony, 10 minut przed końcem pieczenia usuń folię.",
                            image = "lasagna",
                            type = "dania główne"
                        ),
                        RecipeEntity(
                            id = 3,
                            name = "Spaghetti Carbonara",
                            description = "potrawa kuchni włoskiej złożona z makaronu, jajek, pancetty lub guanciale, sera pecorino romano lub parmezanu oraz czarnego pieprzu",
                            ingredients = "makaron spaghetti, jajka, pancetta, pecorino romano, sól, pieprz",
                            instructions = "Zagotuj w dużym garnku wodę i ugotuj makaron al dente. Pokrój boczek w kostkę i podsmaż boczek. Rozmieszaj Fix Spaghetti Carbonara Knorr z wodą. Dodaj śmietanę. Dobrze wymieszaj i zagotuj. Do garnka z ugotowanym makaronem wrzuć starty ser oraz ugotowany Fix. Wymieszaj, podawaj od razu.",
                            image = "carbonara",
                            type = "dania główne"
                        ),
                        RecipeEntity(
                            id = 4,
                            name = "Sernik z budyniem",
                            description = "Rodzaj ciasta deserowego lub deseru uformowanego na kształt ciasta, którego głównym składnikiem jest biały ser.",
                            ingredients = "ser twarogowy 1 kilogram, budyń waniliowy 3 łyżki, cukier 180 gramów, jajka 3 sztuki, śmietanka 30% 100 gramów.",
                            instructions = "Miękkie masło ubijamy z cukrem pudrem, aż powstanie puszysta masa. Po chwili dodajemy po jednym żółtku cały czas mieszając. Następnie dodajemy twaróg sernikowy i nadal mieszamy. Do powstałej masy dodajemy cukier waniliowy, mąkę ziemniaczaną oraz śmietankę. Cały czas mieszamy, aż do połączenia się składników. Białka ubijamy na sztywną pianę. Dodajemy do masy serowej. Delikatnie mieszamy, nie za długo, aby masa się nie napowietrzyła. Formę na sernik wykładamy papierem do pieczenia. Powstałą masę przelewamy do formy i wyrównujemy wierzch. Pieczemy ok 55 min w temperaturze ok 170 C. Studzimy przy uchylonych drzwiczkach. Przed podaniem posypujemy cukrem pudrem.",
                            image = "sernik",
                            type = "desery"
                        ),
                        RecipeEntity(
                            id = 5,
                            name = "Zupa ogórkowa na wędzonce",
                            description = "Zupa gotowana na wywarze mięsnym lub mięsno-warzywnym z dodatkiem startych na tarce ogórków kiszonych lub przecieru ogórkowego i ziemniaków",
                            ingredients = "kiszone ogórki 300 gramów, wędzonka lub kości wędzone 200 gramów, marchewka 1 sztuka, ziemniaki 5 sztuk, koperek 1 pęczek, liść laurowy 2 sztuki, ziele angielskie 2 ziarna, woda 2 litry",
                            instructions = "Do garnka włóż wędzonkę, liść oraz ziele. Wlej 2 litry wody i zagotuj. Wywar gotuj 20-25 minut. Ziemniaki obierz, pokrój w niedużą kostkę i ugotuj do miękkości w wywarze. Pod koniec gotowania wyjmij wędzonkę. Marchewkę zetrzyj na tarce z grubymi oczkami i dodaj do wywaru. Zawartość opakowania Knorr wymieszaj w niewielkiej ilości zimnej wody i wlej do wywaru. Całość doprowadź do wrzenia, następnie zmniejsz ogień i gotuj zupę jeszcze 8 minut. Na koniec dodaj pokrojony drobno koperek. Do zupy możesz również dodać 1 lub 2 łyżki kwaśnej śmietany.",
                            image = "ogor",
                            type = "zupy"
                        ),
                        RecipeEntity(
                            id = 6,
                            name = "Koreczki z fetą",
                            description = "przekąska gotowa do spożycia, powstała w wyniku połączenia delikatnego mięsa śledzi z wyśmienitą marynatą i warzywami bądź owocami.",
                            ingredients = "ser feta 2 opakowania, oliwki 1 słoik, ogórki 1 sztuka, salami 1 opakowanie, pomidorki koktajlowe 200 gramów, winogrona 200 gramów",
                            instructions = "Ser feta pokrój w kostkę o wymiarach 2 cm x 2 cm. Podziel na dwie części.Każdą cześć oprósz jedną z przypraw. Oliwki odcedź z zalewy, pomidorki i winogrona pokrój na pół, a ogórka w pół-plastry. Z tak przygotowanych składników zrób koreczki wedle uznania.",
                            image = "koreczki",
                            type = "przekąski"
                        ),
                        RecipeEntity(
                            id = 7,
                            name = "Wegańskie burgery",
                            description = "Hamburger zrobiony z kotleta, który nie zawiera mięsa, lub kotlet takiego hamburgera.",
                            ingredients = "ryż 125 gramów, czarna fasola 125 gramów, cebula 100 gramów, kmin rzymski 1 sztuka, papryka słodka 1 łyżka, papryka ostra w proszku mielona 1 łyżeczka, orzechy włoskie 125 gramów, panierka panko 25 gramów",
                            instructions = "Komosę ryżową ugotuj według wskazówek na opakowaniu producenta z dodatkiem kostki Knorr. Jarmuż umyj, porwij na drobniejsze listki i zblanszuj. Buraka oraz marchew zetrzyj na tarce, a fasolkę odcedź. Za pomocą ubijaka do puree ziemniaczanego rozgnieć fasolkę z ugotowaną komosą. Dodaj starte warzywa, zblanszowany jarmuż, płatki owsiane, przyprawy Knorr i całość dokładnie wymieszaj. \n" +
                                    "Z masy warzywnej uformuj w dłoniach burgery dopasowane wielkością do bułek. Odstaw je do lodówki na 15 minut. Teraz smaż burgery na patelni teflonowej przez 5-7 minut . Czerwoną cebulę pokrój w piórka, awokado obierz i pokrój w paski, pomidory pokrój na plasterki, sałatę porwij, a bułki przekrój na pół. Bułki posmaruj wegańskim majonezem, połóż wszystkie dodatki, smażone kotlety i podawaj.",
                            image = "burger",
                            type = "dania wegańskie"
                        ),
                        RecipeEntity(
                            id = 8,
                            name = "Złota lemoniada herbaciana",
                            description = "Napój chłodzący o kwaskowatym smaku składający się z wody, cukru i soku cytrynowego, niekiedy gazowany.",
                            ingredients = "Herbata Lipton Yellow Label Tea 1 sztuka, cytryna 2 plastry, brzoskwinia 0.5 sztuki, cukier 3 łyżeczki, gałązka mięty 1 sztuka",
                            instructions = "Zagotuj 200ml wody , dodaj cukier według uznania – jeśli lubisz słodkie napoje dodaj 2 lub trzy łyżeczki. Do gorącej wody wrzuć saszetkę z herbatą Lipton Yellow Label. Napar odstaw do wystygnięcia. Do szklanki wrzuć plastry cytryny, plastry brzoskwini dodaj gałązkę mięty i lód zalej zimną herbatą.",
                            image = "lemoniada",
                            type = "napoje"
                        )
                    )

                    sampleRecipes.forEach { recipe ->
                        println("Inserting recipe: ${recipe.name}, ID: ${recipe.id}, Ingredients: ${recipe.ingredients}")
                    }

                    recipeDao.insertRecipes(sampleRecipes)
                }
            }
        }
    }
