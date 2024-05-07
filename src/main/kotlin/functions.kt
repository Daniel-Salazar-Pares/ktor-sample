import java.util.*

val scan = Scanner(System.`in`)
fun showClientMenu () {
    println("1. Llistar els productes que s’ofereixen\n" +
            "2. Fer comandes sobre els productes que s’ofereixen\n" +
            "3. Fer el pagament de la comanda\n" +
            "4. Revisar comandes (llistar les comandes efectuades: botiga, productes, estat de pagament)\n")
}

fun login () {
    println("Has de fer un registre abans de tot!")
    println("Diga'm el teu nom")
    var nom = scan.nextLine()
}

fun showBotiguerMenu () {
    println("1. Oferir producte\n" +
            "2. Revisar comandes\n" +
            "3. Sortir")
}