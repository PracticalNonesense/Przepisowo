package com.example.cookbook_app
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData


class RecipeViewModel(private val recipeDao: RecipeDao) : ViewModel() {

    val allRecipes: LiveData<List<RecipeEntity>> = recipeDao.getAllRecipesLive()

    fun insertSampleRecipe() {
        val sampleRecipe = RecipeEntity(
            name = "Pizza neapolitańska",
            description = "This is a sample description of a recipe.",
            ingredients = "mąka włoska 300 g, woda niegazowana źródlana 188 ml, drożdże suszone 0,75 g, sól - 9 g, oliwa z oliwek - 6 g, pomidory pelati z puszki - 200 g, mozzarella di bufala - 150 g, sól - 1,5 g, oliwa z oliwek - 30 g",
            instructions = "KROK 1: PRZYGOTOWUJEMY SKŁADNIKI\n" +
                    "Drożdże rozpuść w wodzie, a następnie wlej do misy robota kuchennego. Dodaj także oliwę z oliwek. Wymieszaj na niskich obrotach.\n" +
                    "\n" +
                    "KROK 2: DODAJEMY MĄKĘ\n" +
                    "Do miski dodaj połowę mąki (łyżka po łyżce) i wymieszaj na niskich obrotach. Po ok. 2 minutach powinna uformować się gęsta masa.\n" +
                    "\n" +
                    "KROK 3: DODAJEMY SÓL\n" +
                    "Do misy dodaj sól i mieszaj masę przez co najmniej 20 sekund.\n" +
                    "\n" +
                    "KROK 4: DODAJEMY RESZTĘ MĄKI\n" +
                    "Ostrożnie, łyżka po łyżce wsyp resztę mąki (zostaw sobie 15 g do posypania blatu), cały czas mieszając na niskich obrotach. Całość powinna być mieszana przez 2-3 minuty, aby ciasto wchłonęło wodę.\n" +
                    "\n" +
                    "KROK 5: WYRABIAMY CIASTO\n" +
                    "Podsyp stolnicę mąką, a potem wyłóż ciasto na blat. Zacznij wyrabianie ciasta na blacie, zagniatając do przodu i do siebie, aż do momentu, w którym nabierze sprężystej konsystencji. Wyrabianie powinno zająć mniej więcej 15 minut. Po tym czasie należy zwinąć je w kulkę i wsadzić do pojemnika z przykrywką na 15 minut, by odpoczęło. Po upływie wyznaczonego czasu należy wrócić do wyrabiania i kontynuować je przez kolejne 15 minut. Na tym etapie ciasto będzie jędrne i gładkie.\n" +
                    "\n" +
                    "KROK 6: ODKŁADAMY NA BOK\n" +
                    "Z ciasta uformuj kulkę, włóż do pojemnika i zamknij. Odstaw w ciemne miejsce na 4 godziny, by mogło dojrzeć.\n" +
                    "\n" +
                    "KROK 7: PRZEKŁADAMY POJEMNIK DO LODÓWKI\n" +
                    "Przełóż pojemnik do lodówki na co najmniej 10 godzin (najlepiej na całe 24 godziny).\n" +
                    "\n" +
                    "KROK 8: FORMUJEMY CIASTO\n" +
                    "Wyciągnij ciasto z lodówki, a następnie podziel je na dwie części i z każdej uformuj kulkę, przepuszczając ciasto przez ręce, naciągając zewnętrzne boki do środka, by je delikatnie napowietrzyć i zamknąć.\n" +
                    "\n" +
                    "KROK 9: ODSTAWIAMY KULKI DO PONOWNEGO DOJRZEWANIA\n" +
                    "Włóż obie kulki do pojemnika, a następnie odłóż go w ciemne miejsce na ok. 5 godzin, by ciasto mogło wygarować.\n" +
                    "\n" +
                    "KROK 10: PRZYGOTOWUJEMY DODATKI\n" +
                    "Pomidory przeciśnij pomiędzy palcami do miski i dodaj do nich sól. Mozzarellę pokrój w słupki.\n" +
                    "\n" +
                    "KROK 11: PODSMAŻAMY PIZZĘ\n" +
                    "Rozgrzej patelnię na kuchence do temperatury ok. 300 stopni C. Wyjmij kulkę ciasta z pojemnika i delikatnie zamocz w mące, a potem rozciągnij i połóż na patelni.\n" +
                    "\n" +
                    "KROK 12: UKŁADAMY DODATKI\n" +
                    "Na ułożone na patelni ciasto dodaj pomidory, potem mozzarellę i odrobinę oliwy. Pozwól ciastu przez chwilę się podsmażyć, aby na jego spodzie i brzegach utworzyły się czarne plamki. Brzegi powinny być grube, wyrośnięte i pulchne, a  środek ciasta cieniutki.\n" +
                    "\n" +
                    "KROK 13: WKŁADAMY PATELNIĘ DO PIEKARNIKA\n" +
                    "Rozgrzej piekarnik do temperatury 250 stopni C na funkcji grill. Ściągnij patelnię z kuchenki i włóż lekko uchylonymi drzwiczkami do piekarnika na najwyższą półkę (zaraz pod grzałką). Jeśli to niemożliwe, zamknij drzwiczki Piecz ok. 2 minuty, a jeśli pizza będzie wypiekała się nierównomiernie, obróć patelnię o 180 stopni. Do pieczenia używaj timera, by nie przypalić pizzy.\n" +
                    "\n" +
                    "KROK 14: WYCIĄGAMY PIZZĘ\n" +
                    "Włóż na rękę rękawicę i wyciągnij pizzę z piekarnika. Po chwili przełóż ją na talerz (najlepiej zrobić to przy użyciu łopatki), posyp bazylią i daj jej odpocząć przez kilka minut. Po chwili pizza gotowa jest do krojenia! Możesz przejść do wypiekania kolejnej pizzy.",
            image = "res/drawable/pizza_nea"
        )

        // Użycie viewModelScope do uruchomienia korutyny
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.insertRecipe(sampleRecipe)
        }
    }
}