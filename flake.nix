{
  description = "Spring Boot Bank API";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-25.11";
  };

  outputs = { self, nixpkgs }:
  let
    system = "x86_64-linux";
    pkgs = import nixpkgs { inherit system; };
    gradleWithJdk = pkgs.gradle_9.override {
      java = pkgs.jdk25;
    };

  in {
    devShells.${system}.default = pkgs.mkShell {
      packages = [
        pkgs.jdk25
        gradleWithJdk
      ];

      shellHook = ''
        export JAVA_HOME=${pkgs.jdk25}
        export PATH=${pkgs.jdk25}/bin:$PATH
        echo "Using JAVA_HOME=$JAVA_HOME"
      '';
    };
  };
}
