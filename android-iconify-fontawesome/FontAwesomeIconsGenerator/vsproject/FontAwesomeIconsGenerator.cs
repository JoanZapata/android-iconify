using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Globalization;

/**
 * This tool reads the _variables.scss file that is included in the fontAwesome download.
 * Based on this file a FontAwesomeIcons.java file is generated.
 */
namespace FontAwesomeIconsGenerator
{
    public class FontAwesomeIconsGenerator
    {
        public static void Main(string[] args)
        {
            String usage = @"Usage : FontAwesomeIconsGenerator {path to dir containing _variable.css}\n 
                Example : FontAwesomeIconsGenerator C:\Users\frank\Downloads\font-awesome-4.7.0\font-awesome-4.7.0\scss";

            if (args.Length == 0)
            {
                Console.WriteLine(usage);
            }
            else
            {
                if (!File.Exists(args[0] + @"\_variables.scss"))
                {
                    Console.WriteLine(args[0] + " file could not be found.");
                    Console.WriteLine(usage);
                }
                else
                {
                    string output = "package com.joanzapata.iconify.fonts;\n\n";
                    output += "import com.joanzapata.iconify.Icon;\n\n";
                    output += "public enum FontAwesomeIcons implements Icon {\n";

                    var content = File.ReadAllLines(args[0] + @"\_variables.scss");
                    foreach (var line in content)
                    {
                        if (line.Contains(@"\f"))
                        {
                            var splitLine = line.Split(':');
                            var name = splitLine[0];
                            name = name.Replace("$", "");
                            name = name.Replace("-var", "");
                            name = name.Replace("-", "_");

                            var value = splitLine[1];
                            value = value.Replace("\"\\", "'\\u");
                            value = value.Replace("\";", "'),");

                            var result = name + "(" + value.Trim(); ;

                            output += "    " + result + "\n";
                        }
                    }

                    output = output.Substring(0, output.Length - 2);
                    output += ";";

                    output += "\n\n";
                    output += "    char character;\n\n";
                    output += "    FontAwesomeIcons(char character) {\n";
                    output += "        this.character = character;\n";
                    output += "    }\n\n";
                    output += "    @Override\n";
                    output += "    public String key() {\n";
                    output += "        return name().replace('_', '-');\n";
                    output += "    }\n\n";
                    output += "    @Override\n";
                    output += "    public char character() {\n";
                    output += "        return character;\n";
                    output += "    }\n";
                    output += "}";



                    File.WriteAllText(args[0] + @"\FontAwesomeIcons.java", output);

                    Console.WriteLine("\n\nExport completed...");
                    Console.WriteLine(@"\n\nGenerated file is located at: " + args[0] + @"\FontAwesomeIcons.java");
                }
            }
        }
    }
}
