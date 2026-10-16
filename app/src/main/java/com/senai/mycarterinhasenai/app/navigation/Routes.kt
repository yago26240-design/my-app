package com.senai.mycarterinhasenai.app.navigation
sealed class Routes(val route:String){
 data object Login:Routes("login")
 data object HomeAluno:Routes("homeAluno")
 data object Carteirinha:Routes("carteirinha")
 data object UCAluno:Routes("ucAluno")
 data object HomeProfessor:Routes("homeProfessor")
 data object TurmasProfessor:Routes("turmasProfessor")
 data object UCProfessor:Routes("ucProfessor")
}
