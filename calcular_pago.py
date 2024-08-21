#ejercicio 1 tp nuevo sicos bauti

def pedir_datos_de_pago():
    """
    Solicita los datos de pago al usuario y devuelve un diccionario con la información.
    
    Devuelve:
    ---------
    dict: Diccionario con los datos ingresados por el usuario.
    """
    monto_gastado = float(input("Ingrese el monto gastado por el cliente: "))
    banco_emisor = input("Ingrese el nombre del banco emisor: ")
    tipo_tarjeta = input('Indique si la tarjeta es de “CREDITO” o de “DEBITO”: ').upper()
    dia_semana = int(input("Indique qué día de la semana es hoy (entre 0 y 6): "))
    
    return {
        'monto': monto_gastado,
        'banco': banco_emisor,
        'tipo_tarjeta': tipo_tarjeta,
        'dia_semana': dia_semana
    }

def calcular_monto_a_pagar(monto_gastado, banco_emisor, tipo_tarjeta, dia_semana):
    """
    Calcula el monto final a pagar por el cliente luego de aplicadas las promociones.
    
    Argumentos:
    -----------
    monto_gastado (float): Monto total de la compra realizada por el cliente.
    banco_emisor (string): Nombre del banco emisor de la tarjeta.
    tipo_tarjeta (string): Tipo de tarjeta, "CREDITO" o "DEBITO".
    dia_semana (int): Día de la semana (0 = DOMINGO, 6 = SÁBADO).
    
    Devuelve:
    ---------
    float: Monto final a pagar después de aplicar las promociones.
    """
    descuento = 0
    
    # Martes de shopping
    if dia_semana == 2 and banco_emisor == 'BANCO PONI' and monto_gastado > 20000:
        descuento = 0.30
    
    # Vamos los miércoles
    elif dia_semana == 3 and tipo_tarjeta == 'CREDITO':
        descuento = 0.15
    
    # Delta Jueves
    elif dia_semana == 4 and tipo_tarjeta == 'DEBITO' and banco_emisor == 'BANCO DELTA':
        descuento = 0.20
    
    monto_final = monto_gastado * (1 - descuento)
    return monto_final

if __name__ == "__main__":
    # Obtener datos de pago
    datos_pago = pedir_datos_de_pago()
    monto_final = calcular_monto_a_pagar(
        datos_pago['monto'], 
        datos_pago['banco'], 
        datos_pago['tipo_tarjeta'], 
        datos_pago['dia_semana']
    )
    print(f"Monto final a pagar: {monto_final:.2f}")

