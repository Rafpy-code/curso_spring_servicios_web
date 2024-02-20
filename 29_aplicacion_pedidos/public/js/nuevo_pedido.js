var app=angular.module("comunicacionApp",[]);
		app.controller("comunicacionController",function($scope,$http){
			$scope.productos=[];
			$scope.producto;			
			$scope.unidades;		
			$scope.mostrarTabla=true;			
			
			$scope.cargarProductos=function(){								
				let url = "http://localhost:8000/productos";
				
				$http.get(url).then(function(res){
					$scope.productos=res.data;								
				});
			}
			$scope.pedido=function(){
				let url="http://localhost:7000/alta";
						
				let ob=new Object();
				ob.codigoProducto=$scope.producto;
				ob.unidades=$scope.unidades;
				$http.post(url,ob);
			
			};
		});