import kotlin.system.exitProcess

// aktualny stav a jeho zmeny

enum class Zasoby(var voda: Int, var mlieko: Int, var kava: Int, var pohare: Int, var koruny: Int) {
    AKTUALNY_STAV(voda = 400, mlieko = 540, kava = 120, pohare = 9, koruny = 550),
    ESPRESSO(voda = 250, mlieko = 0, kava = 16, pohare = 1, koruny = 4),
    LATTE(voda = 350, mlieko = 75, kava = 20, pohare = 1, koruny = 7),
    CAPPUCCINO(voda = 200, mlieko = 100, kava = 12, pohare = 1, koruny = 6);
}

enum class MenuAkcia(var buy: String, var fill: String, var take: String, var remaining: String, 
                     var exit1: String) {
    MENU("buy", "fill", "take", "remaining", "exit")
}

var exit = true

// ---------------------------------------------------------------------------------------------

// hlavná funkcia

fun main() {

   do {Cofemat().menu()} while (exit)

}

// --------------------------------------------------------------------------------------

class Cofemat() {

fun menu() {

    println("Write action (buy, fill, take, remaining, exit):")
       
    var vyberAkcie = readln()

        when (vyberAkcie) {           
            MenuAkcia.MENU.buy -> buy()                  
            MenuAkcia.MENU.fill -> fill()                          
            MenuAkcia.MENU.take -> take()                                  
            MenuAkcia.MENU.remaining -> remaining()                              
            MenuAkcia.MENU.exit1 -> exitProcess(0)   
        }
    }

fun buy() {
        medzera()
        
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
    
        var vyber = readln()     //. toInt()
        medzera()
        
        if (vyber == "back") {
            menu()
        } else {
            when (vyber) {
            "1" -> vyber = "ESPRESSO"     
            "2" -> vyber = "LATTE"                                    
            "3" -> vyber = "CAPPUCCINO"
            //"back" -> {vyber = ""; menu()}
            }
                
        if(Zasoby.AKTUALNY_STAV.voda <= Zasoby.valueOf(vyber).voda) {
            println("Sorry, not enough water!")
        }
        else if(Zasoby.AKTUALNY_STAV.mlieko <= Zasoby.valueOf(vyber).mlieko) {
            println("Sorry, not enough milk!")
        }
        else if(Zasoby.AKTUALNY_STAV.kava <= Zasoby.valueOf(vyber).kava) {
            println("Sorry, not enough coffee!")
        }
        else if(Zasoby.AKTUALNY_STAV.pohare <= Zasoby.valueOf(vyber).pohare) {
            println("Sorry, not enough cups!")
        }
        else {
            println("I have enough resources, making you a coffee!")
            
            Zasoby.AKTUALNY_STAV.voda -= Zasoby.valueOf(vyber).voda
            Zasoby.AKTUALNY_STAV.mlieko -= Zasoby.valueOf(vyber).mlieko
            Zasoby.AKTUALNY_STAV.kava -= Zasoby.valueOf(vyber).kava
            Zasoby.AKTUALNY_STAV.pohare--
            Zasoby.AKTUALNY_STAV.koruny += Zasoby.valueOf(vyber).koruny
        }
            
        }

        medzera()
    }

fun remaining() {
    
    medzera()
    
        println("The coffee machine has:")
        println("${Zasoby.AKTUALNY_STAV.voda} ml of water")
        println("${Zasoby.AKTUALNY_STAV.mlieko} ml of milk")
        println("${Zasoby.AKTUALNY_STAV.kava} g of coffee beans")
        println("${Zasoby.AKTUALNY_STAV.pohare} disposable cups")
        println("\$${Zasoby.AKTUALNY_STAV.koruny} of money")
        
        println()
    }

    fun take() {
        
        medzera()
        
        println("I gave you \$${Zasoby.AKTUALNY_STAV.koruny}")
        Zasoby.AKTUALNY_STAV.koruny = 0 

        medzera()
    }

fun fill() {
       
        medzera()

        println("Write how many ml of water you want to add:")
        Zasoby.AKTUALNY_STAV.voda += readln().toInt()

        println("Write how many ml of milk you want to add:")
        Zasoby.AKTUALNY_STAV.mlieko += readln().toInt()

        println("Write how many grams of coffee beans you want to add:")
        Zasoby.AKTUALNY_STAV.kava += readln().toInt()

        println("Write how many disposable cups you want to add:")
        Zasoby.AKTUALNY_STAV.pohare += readln().toInt() 

        medzera()      
    }     
 
    fun medzera() {
        
        println()
    }
    
}
