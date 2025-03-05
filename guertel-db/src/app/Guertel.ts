export class Guertel {
  nummer: String;
  objekt: String;
  abmessung: String;
  material: String;
  fundort: String;
  literaturangabe: String;
  datierung: String;
  bemerkungen: String;

  constructor(
    nummer: String,
    objekt: String,
    abmessung: String,
    material: String,
    fundort: String,
    literaturangabe: String,
    datierung: String,
    bemerkungen: String
  ) {
    this.nummer = nummer;
    this.objekt = objekt;
    this.abmessung = abmessung;
    this.material = material;
    this.fundort = fundort;
    this.literaturangabe = literaturangabe;
    this.datierung = datierung;
    this.bemerkungen = bemerkungen;

  }
}